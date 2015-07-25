package com.zy.app.rating.standard.main.plugin.location.flatrate;

import com.zy.app.rating.standard.main.plugin.location.LocationPlugin;
import com.zy.app.rating.standard.model.LocationResponse;
import com.zy.app.rating.standard.model.Location;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.TrafficPlan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.zy.app.rating.standard.model.buillder.LocationResponseBuilder.aLocationResponse;

@Component
public class FlatRate implements LocationPlugin {

    @Override
    public LocationResponse getLocationResponse(RatingRequest request, TrafficPlan trafficPlan) {
        Location visitedLocation = trafficPlan.getRootLocation();
        List<Location> visitedLocations = new ArrayList<>();
        visitedLocations.add(visitedLocation);

        return aLocationResponse()
                .withTimePlans(visitedLocation.getTimePlans())
                .withVisitedLocations(visitedLocations)
                .build();
    }


}
