package com.zy.app.rating.prepaid.main;

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

import static com.zy.app.rating.prepaid.model.builder.PrepaidRatingResponseBuilder.aPrepaidRatingResponse;
import static com.zy.app.rating.prepaid.model.builder.RatingSessionBuilder.aRatingSession;

/**
 * alexei.bavelski@gmail.com
 * 12/09/15
 */
@Component
public class PrepaidRatingServiceImpl implements PrepaidRatingService {

    @Autowired
    BalanceDao balanceDao;
    @Autowired
    SubscriptionCampaignService subscriptionCampaignService;
    @Autowired
    RatingService ratingService;
    @Autowired
    RatingSessionDao ratingSessionDao;

    @Override
    @Transactional
    public PrepaidRatingResponse startRatingSession(RatingRequest request) {
        PrepaidRatingResponse ratingResponse;
        RatingResponse responseFromCampaigns = subscriptionCampaignService.estimate(request);
        RatingSession ratingSession = aRatingSession()
                .withSessionKey(request.getSessionKey())
                .withChargeDate(request.getChargeDate())
                .withReservedUnits(responseFromCampaigns.getGrantedUnits())
                .build();

        if (responseFromCampaigns.getRatingRequest()==null) {
            ratingSessionDao.createSession(ratingSession);
            ratingResponse =  aPrepaidRatingResponse()
                    .withStatus(PrepaidRatingStatus.FULLY_GRANTED)
                    .withGrantedUnits(responseFromCampaigns.getGrantedUnits())
                    .build();
        } else {
            Balance balance = balanceDao.findBalanceBySubscriptionId(request.getSubscriptionId());
            ratingResponse = ratingService.estimate(balance.getAmount(), responseFromCampaigns.getRatingRequest());
            double price = balance.getAmount() - ratingResponse.getRemainingBalance();
            ratingSession.setPrice(price);
            long totalGrantedUnits = ratingResponse.getGrantedUnits() + responseFromCampaigns.getGrantedUnits();
            ratingSession.setReservedUnits(totalGrantedUnits);
            ratingResponse.setGrantedUnits(totalGrantedUnits);
            ratingSessionDao.createSession(ratingSession);

            balance.setReservedAmount(balance.getReservedAmount()+price);
            balanceDao.updateBalance(balance);
        }

        return ratingResponse;
    }

    @Override
    public PrepaidRatingResponse updateRatingSession(RatingRequest request) {
        return null;
    }

    @Override
    public PrepaidRatingResponse finishRatingSession(RatingRequest request) {
        return null;
    }
}