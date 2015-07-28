package com.zy.app.rating.campaign.model.builder;

import com.zy.app.rating.campaign.main.CampaignType;
import com.zy.app.rating.campaign.model.CampaignSignupRequest;

/**
 * User: alexei.bavelski@gmail.com
 * Date: 28-07-2015
 */
public class CampaignSignupRequestBuilder {
    int subscriptionId;CampaignType campaignType;
    String campaignCode;

    private CampaignSignupRequestBuilder() {
    }

    public static CampaignSignupRequestBuilder aCampaignSignupRequest() {
        return new CampaignSignupRequestBuilder();
    }

    public CampaignSignupRequestBuilder withSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public CampaignSignupRequestBuilder withCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
        return this;
    }

    public CampaignSignupRequestBuilder withCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
        return this;
    }

    public CampaignSignupRequestBuilder but() {
        return aCampaignSignupRequest().withSubscriptionId(subscriptionId).withCampaignType(campaignType).withCampaignCode(campaignCode);
    }

    public CampaignSignupRequest build() {
        CampaignSignupRequest campaignSignupRequest = new CampaignSignupRequest();
        campaignSignupRequest.setSubscriptionId(subscriptionId);
        campaignSignupRequest.setCampaignType(campaignType);
        campaignSignupRequest.setCampaignCode(campaignCode);
        return campaignSignupRequest;
    }
}
