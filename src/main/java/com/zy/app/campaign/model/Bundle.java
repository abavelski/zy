package com.zy.app.campaign.model;

import java.time.LocalDate;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public class Bundle {

    private int id;
    private String campaignCode;
    private int subscriptionCampaignId;
    private int remainingAmount;
    private LocalDate nextResetDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
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
