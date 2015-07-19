package com.zy.app.campaign.main;

import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.RatingResponse;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public interface CampaignPlugin {

    RatingResponse rate (RatingRequest request, String campaignCode);
    void resetIfNeeded(Integer subscriptionId, String campaignCode);
    String getDisplayInfo(Integer subscriptionId, String campaignCode);
    void createNew(Integer subscriptionCampaignId, String campaignCode);

}
