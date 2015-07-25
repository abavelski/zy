package com.zy.app.rating.standard.dao;

import com.zy.app.rating.standard.model.PricePlan;
import com.zy.app.rating.standard.model.TrafficMapping;

import java.util.List;

public interface PricePlanDao {
    PricePlan getCampaignPlanByCode(String code);
    PricePlan getPricePlanByCode(String code);
    List<TrafficMapping> getTrafficMappingsForPricePlan(String code);
    List<String> getAllPricePlans();
}
