package com.zy.app.campaign.model;

import com.zy.app.campaign.main.CampaignType;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public class SubscriptionCampaign {
    private int id;
    private int subscriptionId;
    private CampaignType campaignPlugin;
    private String campaignCode;

    public int getId() {
        return id;
    }

    public CampaignType getCampaignPlugin() {
        return campaignPlugin;
    }

    public void setCampaignPlugin(CampaignType campaignPlugin) {
        this.campaignPlugin = campaignPlugin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }
}
