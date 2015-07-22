package com.zy.app.campaign.dao;

import com.zy.app.campaign.main.CampaignType;
import com.zy.app.campaign.model.Bundle;
import org.springframework.stereotype.Component;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
@Component
public class BundleDaoImpl implements BundleDao {
    @Override
    public int createBundle(Bundle bundle) {
        return 0;
    }

    @Override
    public void updateBundle(Bundle bundle) {

    }

    @Override
    public Bundle getBundleBySubscriptionCampaignIdAndCampaignCode(Integer subscriptionCampaignId, String campaignCode) {
        return null;
    }
}
