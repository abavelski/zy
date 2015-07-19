package com.zy.app.campaign.model.builder;

import com.zy.app.campaign.main.CampaignType;
import com.zy.app.campaign.model.SubscriptionCampaign;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public class SubscriptionCampaignBuilder {
    private int id;
    private int subscriptionId;
    private CampaignType campaignPlugin;
    private String campaignCode;

    private SubscriptionCampaignBuilder() {
    }

    public static SubscriptionCampaignBuilder aSubscriptionCampaign() {
        return new SubscriptionCampaignBuilder();
    }

    public SubscriptionCampaignBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public SubscriptionCampaignBuilder withSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public SubscriptionCampaignBuilder withCampaignPlugin(CampaignType campaignPlugin) {
        this.campaignPlugin = campaignPlugin;
        return this;
    }

    public SubscriptionCampaignBuilder withCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
        return this;
    }

    public SubscriptionCampaignBuilder but() {
        return aSubscriptionCampaign().withId(id).withSubscriptionId(subscriptionId).withCampaignPlugin(campaignPlugin).withCampaignCode(campaignCode);
    }

    public SubscriptionCampaign build() {
        SubscriptionCampaign subscriptionCampaign = new SubscriptionCampaign();
        subscriptionCampaign.setId(id);
        subscriptionCampaign.setSubscriptionId(subscriptionId);
        subscriptionCampaign.setCampaignPlugin(campaignPlugin);
        subscriptionCampaign.setCampaignCode(campaignCode);
        return subscriptionCampaign;
    }
}
