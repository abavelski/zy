package com.zy.app.rating.campaign.dao;

import com.zy.app.rating.campaign.model.SubscriptionCampaign;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public interface SubscriptionCampaignDao {

    List<SubscriptionCampaign> getSubscriptionCampaignsForSubscription(Integer subscriptionId);
    int createSubscriptionCampaign(SubscriptionCampaign subscriptionCampaign);

}
