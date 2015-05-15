package com.zy.app.rating.model.buillder;

import com.zy.app.rating.model.Location;
import com.zy.app.rating.model.TimePlan;

import java.util.List;

/**
 * aba
 * 26/02/15
 */
public class LocationBuilder {
    private String subNumber;
    private String name;
    private List<TimePlan> timePlans;
    private List<Location> children;

    private LocationBuilder() {
    }

    public static LocationBuilder aLocation() {
        return new LocationBuilder();
    }

    public LocationBuilder withSubNumber(String subNumber) {
        this.subNumber = subNumber;
        return this;
    }

    public LocationBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LocationBuilder withTimePlans(List<TimePlan> timePlans) {
        this.timePlans = timePlans;
        return this;
    }

    public LocationBuilder withChildren(List<Location> children) {
        this.children = children;
        return this;
    }

    public LocationBuilder but() {
        return aLocation().withSubNumber(subNumber).withName(name).withTimePlans(timePlans).withChildren(children);
    }

    public Location build() {
        Location location = new Location();
        location.setSubNumber(subNumber);
        location.setName(name);
        location.setTimePlans(timePlans);
        location.setChildren(children);
        return location;
    }
}
