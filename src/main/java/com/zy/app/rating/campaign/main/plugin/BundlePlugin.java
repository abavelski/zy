package com.zy.app.rating.campaign.main.plugin;

import com.zy.app.rating.campaign.dao.BundleDao;
import com.zy.app.rating.campaign.dao.CampaignSettingsDao;
import com.zy.app.rating.campaign.main.CampaignPlugin;
import com.zy.app.rating.campaign.main.CampaignType;
import com.zy.app.rating.campaign.model.Bundle;
import com.zy.app.rating.campaign.model.BundleSettings;
import com.zy.app.rating.campaign.model.SubscriptionCampaign;
import com.zy.app.common.main.UtilService;
import com.zy.app.common.model.ChargeLine;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.zy.app.rating.campaign.model.builder.BundleBuilder.aBundle;
import static com.zy.app.common.model.builder.ChargeLineBuilder.aChargeLine;
import static com.zy.app.rating.standard.model.buillder.RatingResponseBuilder.aRatingResponse;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
@Component
public class BundlePlugin implements CampaignPlugin {

    @Autowired
    CampaignSettingsDao campaignSettingsDao;
    @Autowired
    BundleDao bundleDao;
    @Autowired
    UtilService utilService;

    @Override
    public RatingResponse rate(RatingRequest request, SubscriptionCampaign sc) {
        List<ChargeLine> chargeLines = new ArrayList<>();
        RatingRequest newRatingRequest = null;

        if (request.getAmount()!=0) {
            BundleSettings settings =
                    campaignSettingsDao.readCampaignSettings(CampaignType.BUNDLE, sc.getCampaignCode(), BundleSettings.class);

            Bundle bundle = bundleDao.getBundleBySubscriptionCampaignIdAndCampaignCode(sc.getId(), sc.getCampaignCode());

            long rest = request.getAmount() % settings.getIncrement();
            long full = request.getAmount() - rest + ( rest==0?0:settings.getIncrement() );

            long res = bundle.getRemainingAmount()-full;
            long chargedFromCampaign;
            if (res>=0) {
                chargedFromCampaign = full;
                bundle.setRemainingAmount(res);
            } else {
                chargedFromCampaign = (bundle.getRemainingAmount() / settings.getIncrement()) * settings.getIncrement();
                bundle.setRemainingAmount(bundle.getRemainingAmount() - chargedFromCampaign);
                request.setAmount(request.getAmount()-chargedFromCampaign);
                newRatingRequest = request;
            }
            bundleDao.updateBundle(bundle);

            if (chargedFromCampaign>0) {
                chargeLines.add(aChargeLine()
                        .withSubscriptionId(request.getSubscriptionId())
                        .withChargeDate(request.getChargeDate())
                        .withDescription(settings.getDescription())
                        .withTotal(0d)
                        .build());
            }
        }
        return aRatingResponse()
                .withChargeLines(chargeLines)
                .withRatingRequest(newRatingRequest)
                .build();
    }

    @Override
    public void resetIfNeeded(Integer subscriptionId, String campaignCode) {

    }

    @Override
    public String getDisplayInfo(Integer subscriptionId, String campaignCode) {
        return null;
    }

    @Override
    public void createNew(Integer subscriptionCampaignId, String campaignCode) {
        BundleSettings settings =
                campaignSettingsDao.readCampaignSettings(CampaignType.BUNDLE, campaignCode, BundleSettings.class);
        
        bundleDao.createBundle(aBundle()
                .withCampaignCode(campaignCode)
                .withSubscriptionCampaignId(subscriptionCampaignId)
                .withRemainingAmount(settings.getAmount())
                .build());
    }

    @Override
    public void activate(Integer subscriptionCampaignId, String campaignCode) {
        BundleSettings settings =
                campaignSettingsDao.readCampaignSettings(CampaignType.BUNDLE, campaignCode, BundleSettings.class);

        Bundle bundle = bundleDao.getBundleBySubscriptionCampaignIdAndCampaignCode(subscriptionCampaignId, campaignCode);
        bundle.setNextResetDate(getNextResetDate(settings.getPeriodType(), settings.getPeriodNumber()));
        bundleDao.updateBundle(bundle);
    }

    private LocalDate getNextResetDate(BundleSettings.Period period, Integer nr) {
        LocalDate today = utilService.getCurrentDate();
        switch (period) {
            case MONTH:
                return  today.minusMonths(-1*nr);
            case WEEK:
                return today.minusDays(-7*nr);
            case YEAR:
                return today.minusYears(-1*nr);
            default:
                return null;
        }
    }


}
