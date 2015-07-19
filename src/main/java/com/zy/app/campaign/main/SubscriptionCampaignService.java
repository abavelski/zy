package com.zy.app.campaign.main;

import com.zy.app.campaign.model.CampaignSignupRequest;
import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.RatingResponse;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
public interface SubscriptionCampaignService {

    void signupToCampaign(CampaignSignupRequest request);
    RatingResponse rate(RatingRequest request);


}
