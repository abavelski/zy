package com.zy.app.rating.standard.model.buillder;

import com.zy.app.rating.standard.main.plugin.location.LocationType;
import com.zy.app.rating.standard.main.plugin.time.TimePluginType;
import com.zy.app.rating.standard.model.Location;
import com.zy.app.rating.standard.model.TrafficPlan;

/**
 * aba
 * 26/02/15
 */
public class TrafficPlanBuilder {
    private String ratingCode;
    private LocationType locationPlugin;
    private TimePluginType timePlugin;
    private Location rootLocation;

    private TrafficPlanBuilder() {
    }

    public static TrafficPlanBuilder aTrafficPlan() {
        return new TrafficPlanBuilder();
    }

    public TrafficPlanBuilder withRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
        return this;
    }

    public TrafficPlanBuilder withLocationPlugin(LocationType locationPlugin) {
        this.locationPlugin = locationPlugin;
        return this;
    }

    public TrafficPlanBuilder withTimePlugin(TimePluginType timePlugin) {
        this.timePlugin = timePlugin;
        return this;
    }

    public TrafficPlanBuilder withRootLocation(Location rootLocation) {
        this.rootLocation = rootLocation;
        return this;
    }

    public TrafficPlanBuilder but() {
        return aTrafficPlan().withRatingCode(ratingCode).withLocationPlugin(locationPlugin).withTimePlugin(timePlugin).withRootLocation(rootLocation);
    }

    public TrafficPlan build() {
        TrafficPlan trafficPlan = new TrafficPlan();
        trafficPlan.setRatingCode(ratingCode);
        trafficPlan.setLocationPlugin(locationPlugin);
        trafficPlan.setTimePlugin(timePlugin);
        trafficPlan.setRootLocation(rootLocation);
        return trafficPlan;
    }
}
