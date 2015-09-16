package com.zy.app.rating.standard.main;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.rating.prepaid.model.PrepaidRatingResponse;
import com.zy.app.rating.prepaid.model.PrepaidRatingStatus;
import com.zy.app.rating.standard.dao.PricePlanDao;
import com.zy.app.rating.standard.main.plugin.location.LocationPlugin;
import com.zy.app.rating.standard.main.plugin.location.LocationType;
import com.zy.app.rating.standard.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.standard.main.plugin.rating.RatingType;
import com.zy.app.rating.standard.main.plugin.time.TimePlugin;
import com.zy.app.rating.standard.main.plugin.time.TimePluginType;
import com.zy.app.rating.standard.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zy.app.common.model.builder.ChargeLineBuilder.aChargeLine;
import static com.zy.app.rating.standard.model.buillder.RatingResponseBuilder.aRatingResponse;
import static com.zy.app.rating.standard.model.buillder.TimePlanRequestBuilder.aTimePlanRequest;

@Component
public class RatingServiceImpl implements RatingService {

    @Autowired
    PricePlanDao pricePlanDao;
    @Autowired
    TrafficPlanService trafficPlanService;
    @Autowired
    RatingDescriptionService ratingDescriptionService;
    @Resource
    Map<TimePluginType, TimePlugin> timePlugins;
    @Resource
    Map<RatingType, RatingPlugin> ratingPlugins;
    @Resource
    Map<LocationType, LocationPlugin> locationPlugins;

    @Override
    public RatingResponse rate(RatingRequest request) {
        PricePlan pricePlan = pricePlanDao.getPricePlanByCode(request.getPricePlanCode());
        TrafficPlan trafficPlan = trafficPlanService.getTrafficPlanByRatingCode(pricePlan, request.getRatingCode());

        LocationPlugin locationPlugin = locationPlugins.get(trafficPlan.getLocationPlugin());
        LocationResponse locationResponse = locationPlugin.getLocationResponse(request, trafficPlan);

        TimePlugin timePlugin = timePlugins.get(trafficPlan.getTimePlugin());
        TimePlanRequest timeRequest = aTimePlanRequest()
                    .withChargeDate(request.getChargeDate())
                    .withTimePlans(locationResponse.getTimePlans())
                .build();

        List<ChargeLine> chargeLines = new ArrayList<>();

        List<Charge> charges = timePlugin.getCharges(timeRequest);
        for (Charge charge : charges) {
            RatingPlugin ratingPlugin = ratingPlugins.get(charge.getRatingPlugin());
            ChargeLine line = aChargeLine()
                        .withDescription(ratingDescriptionService.getRatingDescription(charge, locationResponse))
                        .withChargeDate(request.getChargeDate())
                        .withSubscriptionId(request.getSubscriptionId())
                        .withTotal(ratingPlugin.calculatePrice(request.getUnits(), charge.getRate()))
                    .build();
            chargeLines.add(line);
        }
        RatingResponse response = aRatingResponse()
                .withChargeLines(chargeLines)
                .build();
        return response;
    }

    @Override
    public PrepaidRatingResponse estimate(double maxAmount, RatingRequest request) {

        PricePlan pricePlan = pricePlanDao.getCampaignPlanByCode(request.getPricePlanCode());
        TrafficPlan trafficPlan = trafficPlanService.getTrafficPlanByRatingCodeForCampaignRating(pricePlan, request.getRatingCode());
        LocationPlugin locationPlugin = locationPlugins.get(trafficPlan.getLocationPlugin());
        LocationResponse locationResponse = locationPlugin.getLocationResponse(request, trafficPlan);

        TimePlugin timePlugin = timePlugins.get(trafficPlan.getTimePlugin());
        TimePlanRequest timeRequest = aTimePlanRequest()
                .withChargeDate(request.getChargeDate())
                .withTimePlans(locationResponse.getTimePlans())
                .build();

        List<Charge> charges = timePlugin.getCharges(timeRequest);
        double max = maxAmount;
        PrepaidRatingResponse prepaidResponse=null;
        for (Charge charge : charges) {
            RatingPlugin ratingPlugin = ratingPlugins.get(charge.getRatingPlugin());
            prepaidResponse = ratingPlugin.estimate(max, request.getUnits(), charge.getRate());
            if (prepaidResponse.getStatus().equals(PrepaidRatingStatus.INSUFFICIENT_FUNDS)) {
                return prepaidResponse;
            }
            max = prepaidResponse.getRemainingBalance();
        }
        return prepaidResponse;
    }

    @Override
    public List<String> getCampaignCodes(RatingRequest request) {
        PricePlan pricePlan = pricePlanDao.getCampaignPlanByCode(request.getPricePlanCode());
        TrafficPlan trafficPlan = trafficPlanService.getTrafficPlanByRatingCodeForCampaignRating(pricePlan, request.getRatingCode());
        if (trafficPlan==null) {
            return new ArrayList<>();
        }
        LocationPlugin locationPlugin = locationPlugins.get(trafficPlan.getLocationPlugin());
        LocationResponse locationResponse = locationPlugin.getLocationResponse(request, trafficPlan);

        TimePlugin timePlugin = timePlugins.get(trafficPlan.getTimePlugin());
        TimePlanRequest timeRequest = aTimePlanRequest()
                .withChargeDate(request.getChargeDate())
                .withTimePlans(locationResponse.getTimePlans())
                .build();
        return timePlugin.getCampaignCodes(timeRequest);
    }

}
