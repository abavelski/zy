package com.zy.app.rating.standard.model;

import com.zy.app.rating.standard.main.plugin.location.LocationType;
import com.zy.app.rating.standard.main.plugin.time.TimePluginType;

public class TrafficPlan {
    private String ratingCode;
    private LocationType locationPlugin;
    private TimePluginType timePlugin;
    private Location rootLocation;

    public Location getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(Location rootLocation) {
        this.rootLocation = rootLocation;
    }

    public TimePluginType getTimePlugin() {
        return timePlugin;
    }

    public void setTimePlugin(TimePluginType timePlugin) {
        this.timePlugin = timePlugin;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode == null ? null : ratingCode.trim();
    }

    public LocationType getLocationPlugin() {
        return locationPlugin;
    }

    public void setLocationPlugin(LocationType locationPlugin) {
        this.locationPlugin = locationPlugin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrafficPlan that = (TrafficPlan) o;

        if (locationPlugin != that.locationPlugin) return false;
        if (ratingCode != null ? !ratingCode.equals(that.ratingCode) : that.ratingCode != null) return false;
        if (rootLocation != null ? !rootLocation.equals(that.rootLocation) : that.rootLocation != null) return false;
        if (timePlugin != that.timePlugin) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ratingCode != null ? ratingCode.hashCode() : 0;
        result = 31 * result + (locationPlugin != null ? locationPlugin.hashCode() : 0);
        result = 31 * result + (timePlugin != null ? timePlugin.hashCode() : 0);
        result = 31 * result + (rootLocation != null ? rootLocation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrafficPlan{" +
                "ratingCode='" + ratingCode + '\'' +
                ", locationPlugin=" + locationPlugin +
                ", timePlugin=" + timePlugin +
                ", rootLocation=" + rootLocation +
                '}';
    }
}
