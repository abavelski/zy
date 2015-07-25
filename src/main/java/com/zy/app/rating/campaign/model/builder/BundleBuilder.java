package com.zy.app.rating.campaign.model.builder;

import com.zy.app.rating.campaign.model.Bundle;

import java.time.LocalDate;

/**
 * alexei.bavelski@gmail.com
 * 22/07/15
 */
public class BundleBuilder {
    private int id;
    private String campaignCode;
    private int subscriptionCampaignId;
    private long remainingAmount;
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

    public BundleBuilder withCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
        return this;
    }

    public BundleBuilder withSubscriptionCampaignId(int subscriptionCampaignId) {
        this.subscriptionCampaignId = subscriptionCampaignId;
        return this;
    }

    public BundleBuilder withRemainingAmount(long remainingAmount) {
        this.remainingAmount = remainingAmount;
        return this;
    }

    public BundleBuilder withNextResetDate(LocalDate nextResetDate) {
        this.nextResetDate = nextResetDate;
        return this;
    }

    public BundleBuilder but() {
        return aBundle().withId(id).withCampaignCode(campaignCode).withSubscriptionCampaignId(subscriptionCampaignId).withRemainingAmount(remainingAmount).withNextResetDate(nextResetDate);
    }

    public Bundle build() {
        Bundle bundle = new Bundle();
        bundle.setId(id);
        bundle.setCampaignCode(campaignCode);
        bundle.setSubscriptionCampaignId(subscriptionCampaignId);
        bundle.setRemainingAmount(remainingAmount);
        bundle.setNextResetDate(nextResetDate);
        return bundle;
    }
}
