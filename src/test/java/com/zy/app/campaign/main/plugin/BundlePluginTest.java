package com.zy.app.campaign.main.plugin;

import com.zy.app.campaign.dao.BundleDao;
import com.zy.app.campaign.dao.CampaignSettingsDao;
import com.zy.app.campaign.main.CampaignType;
import com.zy.app.campaign.model.Bundle;
import com.zy.app.campaign.model.BundleSettings;
import com.zy.app.campaign.model.SubscriptionCampaign;
import com.zy.app.common.main.UtilService;
import com.zy.app.rating.model.RatingRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.zy.app.campaign.model.builder.BundleBuilder.aBundle;
import static com.zy.app.campaign.model.builder.BundleSettingsBuilder.aBundleSettings;
import static com.zy.app.campaign.model.builder.SubscriptionCampaignBuilder.aSubscriptionCampaign;
import static com.zy.app.rating.model.buillder.RatingRequestBuilder.aRatingRequest;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BundlePluginTest {

    @Mock
    CampaignSettingsDao campaignSettingsDao;
    @Mock
    BundleDao bundleDao;
    @Mock
    UtilService utilService;

    @InjectMocks
    BundlePlugin bundlePlugin;

    @Test
    public void testRate() throws Exception {
        BundleSettings bundleSettings = aBundleSettings()
                .withAmount(600)
                .withCode("voice600")
                .withIncrement(60)
                .build();

        Bundle bundle = aBundle()
                .withSubscriptionCampaignId(1)
                .withRemainingAmount(60)
                .build();

        SubscriptionCampaign sc = aSubscriptionCampaign()
                .withId(1)
                .withCampaignCode("voice600")
                .build();

        RatingRequest rr = aRatingRequest()
                .build();

        when(campaignSettingsDao.readCampaignSettings(CampaignType.BUNDLE, "voice600", BundleSettings.class))
                .thenReturn(bundleSettings);
        when(bundleDao.getBundleBySubscriptionCampaignIdAndCampaignCode(1, "voice600"))
                .thenReturn(bundle);



        bundlePlugin.rate(rr, sc);


    }
}