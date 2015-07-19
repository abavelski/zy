package com.zy.app.campaign.model.builder;

import com.zy.app.campaign.main.CampaignType;
import com.zy.app.campaign.model.Bundle;

import java.time.LocalDate;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public class BundleBuilder {
    private int id;
    private CampaignType campaignType;
    private int subscriptionCampaignId;
    private int remainingAmount;
    private LocalDate nextResetDate;

    private BundleBuilder() {
    }

    public static BundleBuilder aBundle() {
        return new BundleBuilder();
    }

    public BundleBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public BundleBuilder withCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
        return this;
    }

    public BundleBuilder withSubscriptionCampaignId(int subscriptionCampaignId) {
        this.subscriptionCampaignId = subscriptionCampaignId;
        return this;
    }

    public BundleBuilder withRemainingAmount(int remainingAmount) {
        this.remainingAmount = remainingAmount;
        return this;
    }

    public BundleBuilder withNextResetDate(LocalDate nextResetDate) {
        this.nextResetDate = nextResetDate;
        return this;
    }

    public BundleBuilder but() {
        return aBundle().withId(id).withCampaignType(campaignType).withSubscriptionCampaignId(subscriptionCampaignId).withRemainingAmount(remainingAmount).withNextResetDate(nextResetDate);
    }

    public Bundle build() {
        Bundle bundle = new Bundle();
        bundle.setId(id);
        bundle.setCampaignType(campaignType);
        bundle.setSubscriptionCampaignId(subscriptionCampaignId);
        bundle.setRemainingAmount(remainingAmount);
        bundle.setNextResetDate(nextResetDate);
        return bundle;
    }
}
