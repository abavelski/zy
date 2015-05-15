package com.zy.app.rating.model;

import java.util.List;

public class Location {
    private String subNumber;
    private String name;
    private List<TimePlan> timePlans;
    private List<Location> children;

    public List<TimePlan> getTimePlans() {
        return timePlans;
    }

    public void setTimePlans(List<TimePlan> timePlans) {
        this.timePlans = timePlans;
    }

    public List<Location> getChildren() {
        return children;
    }

    public void setChildren(List<Location> children) {
        this.children = children;
    }

    public String getSubNumber() {
        return subNumber;
    }

    public void setSubNumber(String subNumber) {
        this.subNumber = subNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (children != null ? !children.equals(location.children) : location.children != null) return false;
        if (name != null ? !name.equals(location.name) : location.name != null) return false;
        if (subNumber != null ? !subNumber.equals(location.subNumber) : location.subNumber != null) return false;
        if (timePlans != null ? !timePlans.equals(location.timePlans) : location.timePlans != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subNumber != null ? subNumber.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (timePlans != null ? timePlans.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "subNumber='" + subNumber + '\'' +
                ", name='" + name + '\'' +
                ", timePlans=" + timePlans +
                ", children=" + children +
                '}';
    }
}
