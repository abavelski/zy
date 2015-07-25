package com.zy.app.rating.campaign.model;

import com.zy.app.rating.campaign.main.CampaignType;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public class CampaignSignupRequest {
    int subscriptionId;
    CampaignType campaignType;
    String campaignCode;

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public CampaignType getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }
}
