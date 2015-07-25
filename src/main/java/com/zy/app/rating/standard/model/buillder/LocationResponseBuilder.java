package com.zy.app.rating.standard.model.buillder;

import com.zy.app.rating.standard.model.Location;
import com.zy.app.rating.standard.model.LocationResponse;
import com.zy.app.rating.standard.model.TimePlan;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 25/07/15
 */
public class LocationResponseBuilder {
    private List<Location> visitedLocations;
    private List<TimePlan> timePlans;

    private LocationResponseBuilder() {
    }

    public static LocationResponseBuilder aLocationResponse() {
        return new LocationResponseBuilder();
    }

    public LocationResponseBuilder withVisitedLocations(List<Location> visitedLocations) {
        this.visitedLocations = visitedLocations;
        return this;
    }

    public LocationResponseBuilder withTimePlans(List<TimePlan> timePlans) {
        this.timePlans = timePlans;
        return this;
    }

    public LocationResponseBuilder but() {
        return aLocationResponse().withVisitedLocations(visitedLocations).withTimePlans(timePlans);
    }

    public LocationResponse build() {
        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setVisitedLocations(visitedLocations);
        locationResponse.setTimePlans(timePlans);
        return locationResponse;
    }
}
