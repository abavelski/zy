package com.zy.app.rating.main;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.rating.dao.PricePlanDao;
import com.zy.app.rating.main.plugin.location.LocationPlugin;
import com.zy.app.rating.model.LocationResponse;
import com.zy.app.rating.main.plugin.location.LocationType;
import com.zy.app.rating.main.plugin.rating.RatingPlugin;
import com.zy.app.rating.main.plugin.rating.RatingType;
import com.zy.app.rating.model.TimePlanRequest;
import com.zy.app.rating.main.plugin.time.TimePlugin;
import com.zy.app.rating.main.plugin.time.TimePluginType;
import com.zy.app.rating.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.zy.app.common.model.ChargeLineBuilder.aChargeLine;
import static com.zy.app.rating.model.buillder.RatingResponseBuilder.aRatingResponse;
import static com.zy.app.rating.model.buillder.TimePlanRequestBuilder.aTimePlanRequest;

@Component
public class RatingServiceImpl implements RatingService {

    @Autowired
    PricePlanDao pricePlanDao;
    @Autowired
    TrafficPlanService trafficPlanService;
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
                        .withDescription(charge.getRatingPlugin().toString())
                        .withChargeDate(request.getChargeDate())
                        .withSubscriptionId(request.getSubscriptionId())
                        .withTotal(ratingPlugin.calculatePrice(request, charge.getRate()))
                    .build();
            chargeLines.add(line);
        }
        RatingResponse response = aRatingResponse()
                .withChargeLines(chargeLines)
                .withLocationNames(getVisitedLocationsNames(locationResponse))
                .build();
        return response;
    }


    private List<String> getVisitedLocationsNames(LocationResponse response) {
        List<String> locationNames = new ArrayList<>();
        if (response.getVisitedLocations() !=null) {
            locationNames.addAll(response.getVisitedLocations()
                    .stream()
                    .map(Location::getName)
                    .collect(Collectors.toList()));
        }
        return  locationNames;
    }


}
