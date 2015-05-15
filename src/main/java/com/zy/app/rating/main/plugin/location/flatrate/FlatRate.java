package com.zy.app.rating.main.plugin.location.flatrate;

import com.zy.app.rating.main.plugin.location.LocationPlugin;
import com.zy.app.rating.model.LocationResponse;
import com.zy.app.rating.model.Location;
import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.TrafficPlan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlatRate implements LocationPlugin {

    @Override
    public LocationResponse getLocationResponse(RatingRequest request, TrafficPlan trafficPlan) {
        LocationResponse response = new LocationResponse();
        Location visitedLocation = trafficPlan.getRootLocation();
        response.setTimePlans(visitedLocation.getTimePlans());
        List<Location> visitedLocations = new ArrayList<>();
        visitedLocations.add(visitedLocation);
        response.setVisitedLocations(visitedLocations);
        return response;
    }


}
