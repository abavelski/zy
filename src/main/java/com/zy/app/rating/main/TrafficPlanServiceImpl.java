package com.zy.app.rating.main;

import com.zy.app.rating.main.plugin.location.LocationType;
import com.zy.app.rating.main.plugin.time.TimePluginType;
import com.zy.app.rating.model.PricePlan;
import com.zy.app.rating.model.RatingCodeRequest;
import com.zy.app.rating.model.TrafficMapping;
import com.zy.app.rating.model.TrafficPlan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrafficPlanServiceImpl implements TrafficPlanService {

    @Override
    public TrafficPlan getTrafficPlanByRatingCode(PricePlan pricePlan, String code) {
        List<TrafficPlan> plans  = pricePlan.getTrafficPlans();
        for (TrafficPlan plan : plans) {
            if (plan.getRatingCode().equals(code)) {
                updateDefaultLocationPlugin(plan);
                updateDefaultTimePlugin(plan);
                return plan;
            }
        }
        throw new RuntimeException("Rate plan not found.");
    }

    @Override
    public String getRatingCodeFromUsage(RatingCodeRequest request) {
        List<TrafficMapping> mappings = request.getMappings();
        for (TrafficMapping mapping : mappings) {
            if (mapping.getTrafficType()==request.getTrafficType() &&
                    mapping.getUsageType() == request.getUsageType()) {
                return mapping.getRatingCode();
            }
        }
        throw new RuntimeException("Rating code not mapped");
    }

    private void updateDefaultTimePlugin(TrafficPlan plan) {
        if (plan.getTimePlugin()==null) {
            plan.setTimePlugin(TimePluginType.FLAT);
        }
    }

    private void updateDefaultLocationPlugin(TrafficPlan plan) {
       if (plan.getLocationPlugin()==null) {
           plan.setLocationPlugin(LocationType.FLAT);
       }
    }

}
