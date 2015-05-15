package com.zy.app.rating.model;

import java.util.List;

public class LocationResponse {

    private List<Location> visitedLocations;
    private List<TimePlan> timePlans;

    public List<Location> getVisitedLocations() {
        return visitedLocations;
    }

    public void setVisitedLocations(List<Location> visitedLocations) {
        this.visitedLocations = visitedLocations;
    }

    public List<TimePlan> getTimePlans() {
        return timePlans;
    }

    public void setTimePlans(List<TimePlan> timePlans) {
        this.timePlans = timePlans;
    }
}
