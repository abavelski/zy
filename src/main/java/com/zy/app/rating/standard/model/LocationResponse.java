package com.zy.app.rating.standard.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationResponse that = (LocationResponse) o;

        if (visitedLocations != null ? !visitedLocations.equals(that.visitedLocations) : that.visitedLocations != null)
            return false;
        return !(timePlans != null ? !timePlans.equals(that.timePlans) : that.timePlans != null);

    }

    @Override
    public int hashCode() {
        int result = visitedLocations != null ? visitedLocations.hashCode() : 0;
        result = 31 * result + (timePlans != null ? timePlans.hashCode() : 0);
        return result;
    }
}
