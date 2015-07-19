package com.zy.app.campaign.main.plugin;

import com.zy.app.campaign.dao.BundleDao;
import com.zy.app.campaign.dao.CampaignSettingsDao;
import com.zy.app.campaign.main.CampaignPlugin;
import com.zy.app.campaign.main.CampaignType;
import com.zy.app.campaign.model.BundleSettings;
import com.zy.app.common.main.UtilService;
import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.zy.app.campaign.model.builder.BundleBuilder.aBundle;

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
    public RatingResponse rate(RatingRequest request, String campaignCode) {
        return null;
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
        
        int id = bundleDao.createBundle(aBundle()
                    .withCampaignType(CampaignType.BUNDLE)
                    .withSubscriptionCampaignId(subscriptionCampaignId)
                    .withRemainingAmount(settings.getAmount())
                    .withNextResetDate(getNextResetDate(settings.getPeriodType(), settings.getPeriodNumber()))
                .build());
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
