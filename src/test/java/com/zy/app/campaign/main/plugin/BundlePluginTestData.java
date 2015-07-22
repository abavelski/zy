package com.zy.app.campaign.main.plugin;

import com.zy.app.campaign.model.Bundle;
import com.zy.app.campaign.model.BundleSettings;
import com.zy.app.campaign.model.SubscriptionCampaign;
import com.zy.app.rating.model.RatingRequest;

import static com.zy.app.campaign.model.builder.BundleBuilder.aBundle;
import static com.zy.app.campaign.model.builder.BundleSettingsBuilder.aBundleSettings;
import static com.zy.app.campaign.model.builder.SubscriptionCampaignBuilder.aSubscriptionCampaign;
import static com.zy.app.rating.model.buillder.RatingRequestBuilder.aRatingRequest;

/**
 * User: alexei.bavelski@gmail.com
 * Date: 22-07-2015
 */
public class BundlePluginTestData {

    public BundleSettings bundleSettings = aBundleSettings()
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




}
