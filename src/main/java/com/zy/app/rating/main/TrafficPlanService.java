package com.zy.app.rating.main;

import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.rating.model.PricePlan;
import com.zy.app.rating.model.RatingCodeRequest;
import com.zy.app.rating.model.TrafficMapping;
import com.zy.app.rating.model.TrafficPlan;

import java.util.List;


public interface TrafficPlanService {

    public TrafficPlan getTrafficPlanByRatingCode(PricePlan pricePlan, String code);
    public String getRatingCodeFromUsage(RatingCodeRequest ratingCodeRequest);
}
