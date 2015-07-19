package com.zy.app.campaign.dao;

import com.zy.app.campaign.main.CampaignType;
import com.zy.app.campaign.model.Bundle;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public interface BundleDao {

    int createBundle( Bundle bundle);
    void updateBundle( Bundle bundle);
    Bundle getBundleBySubscriptionCampaignIdAndCampaignType(Integer subscriptionCampaignId, CampaignType campaignType);

}
