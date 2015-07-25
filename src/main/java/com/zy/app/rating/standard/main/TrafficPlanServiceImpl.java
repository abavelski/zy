package com.zy.app.rating.standard.main;

import com.zy.app.rating.standard.main.plugin.location.LocationType;
import com.zy.app.rating.standard.main.plugin.time.TimePluginType;
import com.zy.app.rating.standard.model.PricePlan;
import com.zy.app.rating.standard.model.RatingCodeRequest;
import com.zy.app.rating.standard.model.TrafficMapping;
import com.zy.app.rating.standard.model.TrafficPlan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrafficPlanServiceImpl implements TrafficPlanService {

    private TrafficPlan getTrafficPlan(List<TrafficPlan> plans, String code) {
        TrafficPlan result = null;
        for (TrafficPlan plan : plans) {
            if (plan.getRatingCode().equals(code)) {
                updateDefaultLocationPlugin(plan);
                updateDefaultTimePlugin(plan);
                result = plan;
                break;
            }
        }
        return result;
    }

    @Override
    public TrafficPlan getTrafficPlanByRatingCode(PricePlan pricePlan, String code) {
        TrafficPlan plan = getTrafficPlan(pricePlan.getTrafficPlans(), code);
        if (plan==null) {
            throw new RuntimeException("Rate plan not found.");
        }
        return plan;
    }

    @Override
    public TrafficPlan getTrafficPlanByRatingCodeForCampaignRating(PricePlan pricePlan, String code) {
        return getTrafficPlan(pricePlan.getTrafficPlans(), code);
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
