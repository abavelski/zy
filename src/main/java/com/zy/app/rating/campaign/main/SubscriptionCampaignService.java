package com.zy.app.rating.campaign.main;

import com.zy.app.rating.campaign.model.CampaignSignupRequest;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public interface SubscriptionCampaignService {

    void signupToCampaign(CampaignSignupRequest request);
    void activateCampaignsForSubscription(Integer subscriptionId);
    RatingResponse rate(RatingRequest request);
    RatingResponse estimate(RatingRequest request);


}
