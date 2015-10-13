package com.zy.app.rating.prepaid.main;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.rating.campaign.main.SubscriptionCampaignService;
import com.zy.app.rating.prepaid.dao.BalanceDao;
import com.zy.app.rating.prepaid.dao.RatingSessionDao;
import com.zy.app.rating.prepaid.model.Balance;
import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;
import com.zy.app.rating.prepaid.model.RatingSession;
import com.zy.app.rating.standard.main.RatingService;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.zy.app.common.util.MathUtil.round;
import static com.zy.app.rating.prepaid.model.builder.PrepaidRatingResponseBuilder.aPrepaidRatingResponse;
import static com.zy.app.rating.prepaid.model.builder.RatingSessionBuilder.aRatingSession;

/**
 * alexei.bavelski@gmail.com
 * 12/09/15
 */
@Component
public class PrepaidRatingServiceImpl implements PrepaidRatingService {

    @Autowired
    BalanceService balanceService;
    @Autowired
    SubscriptionCampaignService subscriptionCampaignService;
    @Autowired
    RatingService ratingService;
    @Autowired
    RatingSessionDao ratingSessionDao;

    private RatingSession createRatingSession(RatingRequest request) {
        return aRatingSession()
                .withSessionKey(request.getSessionKey())
                .withChargeDate(request.getChargeDate())
                .build();
    }

    private PrepaidRatingResponse fullyGrantedFromCampaign(long units) {
      return aPrepaidRatingResponse()
              .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
              .withGrantedUnits(units)
              .build();
    }

    @Override
    @Transactional
    public PrepaidRatingResponse startRatingSession(RatingRequest request) {
        PrepaidRatingResponse ratingResponse;

        RatingSession ratingSession = createRatingSession(request);
        RatingResponse responseFromCampaigns = subscriptionCampaignService.estimate(request);

        long grantedUnitsFromCampaign = responseFromCampaigns.getGrantedUnits();

        if (responseFromCampaigns.getRatingRequest()==null) {
            ratingSession.setReservedUnits(grantedUnitsFromCampaign);
            ratingSessionDao.createSession(ratingSession);
            ratingResponse = fullyGrantedFromCampaign(grantedUnitsFromCampaign);
        } else {
            double balanceAmount = balanceService.getRemainingBalanceExclVat(request.getSubscriptionId());
            ratingResponse = ratingService.estimate(balanceAmount, responseFromCampaigns.getRatingRequest());
            double totalSessionPrice = round(balanceAmount - ratingResponse.getRemainingBalance(), 2);
            long grantedUnitsFromStandardRating = ratingResponse.getGrantedUnits();
            long totalGrantedUnits = grantedUnitsFromStandardRating + grantedUnitsFromCampaign;

            if (totalGrantedUnits==0) {
                ratingResponse.setStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS);
            } else if (totalGrantedUnits<request.getUnits()) {
                ratingResponse.setStatus(PrepaidRatingStatus.PARTIALLY_GRANTED);
            }
            if (grantedUnitsFromStandardRating >0) {
                balanceService.reserveAmountExclVat(request.getSubscriptionId(), 0, totalSessionPrice);
            }

            ratingResponse.setGrantedUnits(totalGrantedUnits);
            ratingSession.setPrice(totalSessionPrice);
            ratingSession.setReservedUnits(totalGrantedUnits);
            if (ratingSession.getReservedUnits()>0) {
                ratingSessionDao.createSession(ratingSession);
            }
        }
        return ratingResponse;
    }

    @Override
    @Transactional
    public PrepaidRatingResponse updateRatingSession(long usedUnits, RatingRequest request) {
        PrepaidRatingResponse ratingResponse;

        long requestedUnits = request.getUnits();

        RatingSession session = ratingSessionDao.findSession(request.getSessionKey());
        long oldTotalUnits = session.getUsedUnits()+session.getReservedUnits();
        double oldSessionPrice = session.getPrice();

        session.setUsedUnits(session.getUsedUnits()+usedUnits);
        long remainingReservedUnits = session.getReservedUnits() - usedUnits;
        session.setReservedUnits((remainingReservedUnits >0)?remainingReservedUnits:0);

        long totalRequestedUnits = requestedUnits + session.getReservedUnits() + session.getUsedUnits();
        request.setUnits(totalRequestedUnits);

        RatingResponse responseFromCampaigns = subscriptionCampaignService.estimate(request);

        if (responseFromCampaigns.getRatingRequest()==null) {
            ratingResponse = fullyGrantedFromCampaign(requestedUnits);
            session.setReservedUnits(session.getReservedUnits()+requestedUnits);
        } else {
            double balanceAmount = balanceService.getRemainingBalanceExclVat(request.getSubscriptionId());
            ratingResponse = ratingService.estimate(balanceAmount, responseFromCampaigns.getRatingRequest());
            double newPrice = round(balanceAmount - ratingResponse.getRemainingBalance(), 2);
            session.setPrice(newPrice);
            long totalGrantedUnits = ratingResponse.getGrantedUnits() + responseFromCampaigns.getGrantedUnits();

            if (totalGrantedUnits-oldTotalUnits==0) {
                ratingResponse.setStatus(PrepaidRatingStatus.INSUFFICIENT_FUNDS);
            } else if (totalGrantedUnits<request.getUnits()) {
                ratingResponse.setStatus(PrepaidRatingStatus.PARTIALLY_GRANTED);
            }
            if (ratingResponse.getGrantedUnits()-oldTotalUnits>0) {
                balanceService.reserveAmountExclVat(request.getSubscriptionId(), oldSessionPrice, newPrice);
            }
            session.setReservedUnits(totalGrantedUnits-session.getUsedUnits());
            ratingResponse.setGrantedUnits(totalGrantedUnits-oldTotalUnits);
        }
        ratingSessionDao.updateSession(session);
        return ratingResponse;
    }

    @Override
    @Transactional
    public RatingResponse terminateRatingSession(RatingRequest request) {
        RatingSession session = ratingSessionDao.findSession(request.getSessionKey());

        long totalUnits = session.getUsedUnits()+request.getUnits();
        request.setUnits(totalUnits);

        RatingResponse response;
        RatingResponse campaignResponse = subscriptionCampaignService.rate(request);
        if (campaignResponse.getRatingRequest()==null) {
            response = campaignResponse;
        } else {
            response = ratingService.rate(request);
            response.getChargeLines().addAll(campaignResponse.getChargeLines());
        }

        double total = 0;
        for (ChargeLine chargeLine : response.getChargeLines()) {
            total+=chargeLine.getTotal();
        }
        //update balance
        balanceService.chargeExclVat(request.getSubscriptionId(), session.getPrice(), total);

        //delete session
        ratingSessionDao.deleteSession(session.getSessionKey());
        return response;
    }
}
