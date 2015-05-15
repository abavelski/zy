package com.zy.app.rating.dao;

import com.zy.app.rating.model.PricePlan;
import com.zy.app.rating.model.TrafficMapping;

import java.util.List;

public interface PricePlanDao {
    PricePlan getPricePlanByCode(String code);
    List<TrafficMapping> getTrafficMappingsForPricePlan(String code);
    List<String> getAllPricePlans();
}
