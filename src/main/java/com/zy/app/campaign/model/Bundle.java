package com.zy.app.campaign.model;

import com.zy.app.campaign.main.CampaignType;

import java.time.LocalDate;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public class Bundle {

    private int id;
    private CampaignType campaignType;
    private int subscriptionCampaignId;
    private int remainingAmount;
    private LocalDate nextResetDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CampaignType getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
    }

    public int getSubscriptionCampaignId() {
        return subscriptionCampaignId;
    }

    public void setSubscriptionCampaignId(int subscriptionCampaignId) {
        this.subscriptionCampaignId = subscriptionCampaignId;
    }

    public int getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(int remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public LocalDate getNextResetDate() {
        return nextResetDate;
    }

    public void setNextResetDate(LocalDate nextResetDate) {
        this.nextResetDate = nextResetDate;
    }
}
