package com.zy.app.rating.campaign.main.plugin;

import com.zy.app.rating.campaign.model.Bundle;
import com.zy.app.rating.campaign.model.BundleSettings;
import com.zy.app.rating.campaign.model.SubscriptionCampaign;
import com.zy.app.rating.campaign.model.builder.BundleBuilder;
import com.zy.app.rating.standard.model.buillder.RatingRequestBuilder;

import java.time.LocalDateTime;
import java.time.Month;

import static com.zy.app.rating.campaign.model.builder.BundleBuilder.aBundle;
import static com.zy.app.rating.campaign.model.builder.BundleSettingsBuilder.aBundleSettings;
import static com.zy.app.rating.campaign.model.builder.SubscriptionCampaignBuilder.aSubscriptionCampaign;
import static com.zy.app.rating.standard.model.buillder.RatingRequestBuilder.aRatingRequest;

/**
 * User: alexei.bavelski@gmail.com
 * Date: 22-07-2015
 */
public class BundlePluginTestData {

    public  static LocalDateTime CHARGEDATE = LocalDateTime.of(2015, Month.FEBRUARY, 5, 8, 0);

    public static BundleSettings bundleSettings() {
        return aBundleSettings()
                .withAmount(600)
                .withCode("voice600")
                .withDescription("test")
                .withIncrement(60)
                .build();
    }

    public static BundleBuilder voice600 = aBundle()
            .withId(1)
            .withCampaignCode("voice600")
            .withSubscriptionCampaignId(1)
            .withRemainingAmount(540);

    public static Bundle bundleWith540SecondsLeft() {
        return voice600.build();
    }

    public static Bundle bundleWith0SecondsLeft() {
        return voice600.but().withRemainingAmount(0).build();
    }

    public static Bundle bundleWith480SecondsLeft() {
        return voice600.but().withRemainingAmount(480).build();
    }



    public static SubscriptionCampaign subscriptionCampaign() {
        return aSubscriptionCampaign()
                .withId(1)
                .withCampaignCode("voice600")
                .build();
    }

    public static RatingRequestBuilder ratingRequest = aRatingRequest()
            .withRatingCode("voice-home")
            .withSubscriptionId(1)
            .withChargeDate(CHARGEDATE);



}
