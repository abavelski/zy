package com.zy.app.rating.campaign.model;

import java.time.LocalDate;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public class Bundle {

    private int id;
    private String campaignCode;
    private int subscriptionCampaignId;
    private long remainingAmount;
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

    public long getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(long remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public LocalDate getNextResetDate() {
        return nextResetDate;
    }

    public void setNextResetDate(LocalDate nextResetDate) {
        this.nextResetDate = nextResetDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bundle bundle = (Bundle) o;

        if (id != bundle.id) return false;
        if (subscriptionCampaignId != bundle.subscriptionCampaignId) return false;
        if (remainingAmount != bundle.remainingAmount) return false;
        if (campaignCode != null ? !campaignCode.equals(bundle.campaignCode) : bundle.campaignCode != null)
            return false;
        return !(nextResetDate != null ? !nextResetDate.equals(bundle.nextResetDate) : bundle.nextResetDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (campaignCode != null ? campaignCode.hashCode() : 0);
        result = 31 * result + subscriptionCampaignId;
        result = 31 * result + (int) (remainingAmount ^ (remainingAmount >>> 32));
        result = 31 * result + (nextResetDate != null ? nextResetDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "id=" + id +
                ", campaignCode='" + campaignCode + '\'' +
                ", subscriptionCampaignId=" + subscriptionCampaignId +
                ", remainingAmount=" + remainingAmount +
                ", nextResetDate=" + nextResetDate +
                '}';
    }
}
