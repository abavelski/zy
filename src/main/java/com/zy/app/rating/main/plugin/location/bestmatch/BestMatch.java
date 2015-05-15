package com.zy.app.rating.main.plugin.location.bestmatch;

import com.zy.app.rating.main.plugin.location.LocationPlugin;
import com.zy.app.rating.model.LocationResponse;
import com.zy.app.rating.model.Location;
import com.zy.app.rating.model.RatingRequest;
import com.zy.app.rating.model.TimePlan;
import com.zy.app.rating.model.TrafficPlan;

import java.util.ArrayList;
import java.util.List;

public class BestMatch implements LocationPlugin {

    @Override
    public LocationResponse getLocationResponse(RatingRequest request, TrafficPlan trafficPlan) {
        LocationResponse response = new LocationResponse();
        Location rootLocation =  trafficPlan.getRootLocation();
        List<Location> visitedLocations = new ArrayList<>();
        parseLocationTree(rootLocation, request.getDestination(), visitedLocations);
        response.setVisitedLocations(visitedLocations);
        response.setTimePlans(findTimePlans(visitedLocations));
        return response;
    }

    private void parseLocationTree(Location location, String destination, List<Location> visitedLocations) {
        visitedLocations.add(location);
        List<Location> children = location.getChildren();
        if (children!=null) {
            for (Location child : children) {
                String subNumber = child.getSubNumber();
                if (destination.startsWith(subNumber)) {
                    parseLocationTree(child, destination.substring(subNumber.length()), visitedLocations);
                }
            }
        }

    }

    private List<TimePlan> findTimePlans(List<Location> visitedLocations) {
        List<TimePlan> timePlans = null;
        for (int i = visitedLocations.size()-1; i >=0; i--) {
            if (visitedLocations.get(i).getTimePlans()!=null) {
                timePlans = visitedLocations.get(i).getTimePlans();
                break;
            }
        }
        return timePlans;
    }

}
