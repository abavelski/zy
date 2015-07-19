package com.zy.app.campaign.dao;

import com.zy.app.campaign.model.SubscriptionCampaign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
@Component
public class SubscriptionCampaignDaoImpl implements SubscriptionCampaignDao {
    @Override
    public List<SubscriptionCampaign> getSubscriptionCampaignsForSubscription(Integer subscriptionId) {
        return null;
    }

    @Override
    public int createSubscriptionCampaign(SubscriptionCampaign subscriptionCampaign) {
        return 0;
    }
}
