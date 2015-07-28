package com.zy.app.rating.campaign.model;

import com.zy.app.rating.campaign.main.CampaignType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionCampaign that = (SubscriptionCampaign) o;

        if (id != that.id) return false;
        if (subscriptionId != that.subscriptionId) return false;
        if (campaignCode != null ? !campaignCode.equals(that.campaignCode) : that.campaignCode != null) return false;
        if (campaignPlugin != that.campaignPlugin) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + subscriptionId;
        result = 31 * result + (campaignPlugin != null ? campaignPlugin.hashCode() : 0);
        result = 31 * result + (campaignCode != null ? campaignCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubscriptionCampaign{" +
                "id=" + id +
                ", subscriptionId=" + subscriptionId +
                ", campaignPlugin=" + campaignPlugin +
                ", campaignCode='" + campaignCode + '\'' +
                '}';
    }
}
