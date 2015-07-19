package com.zy.app.campaign.dao;

import com.zy.app.campaign.model.SubscriptionCampaign;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public interface SubscriptionCampaignDao {

    List<SubscriptionCampaign> getSubscriptionCampaignsForSubscription(Integer subscriptionId);
    int createSubscriptionCampaign(SubscriptionCampaign subscriptionCampaign);

}
