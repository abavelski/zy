package com.zy.app.campaign.model.builder;

import com.zy.app.campaign.model.Bundle;

import java.time.LocalDate;

/**
 * User: alexei.bavelski@nordea.com
 * Date: 22-07-2015
 */
public class BundleBuilder {
    private int id;
    private String campaignCode;
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

    public BundleBuilder withCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
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
