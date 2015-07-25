package com.zy.app.rating.standard.main;

import com.zy.app.rating.standard.model.PricePlan;
import com.zy.app.rating.standard.model.RatingCodeRequest;
import com.zy.app.rating.standard.model.TrafficPlan;


public interface TrafficPlanService {

    public TrafficPlan getTrafficPlanByRatingCode(PricePlan pricePlan, String code);
    public TrafficPlan getTrafficPlanByRatingCodeForCampaignRating(PricePlan pricePlan, String code);
    public String getRatingCodeFromUsage(RatingCodeRequest ratingCodeRequest);
}
