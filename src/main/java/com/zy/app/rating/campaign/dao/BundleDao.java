package com.zy.app.rating.campaign.dao;

import com.zy.app.rating.campaign.model.Bundle;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public interface BundleDao {

    int createBundle( Bundle bundle);
    void updateBundle( Bundle bundle);
    Bundle getBundleBySubscriptionCampaignIdAndCampaignCode(Integer subscriptionCampaignId, String campaignCode);

}
