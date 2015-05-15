package com.zy.app.rating.model;

import java.util.List;

public class PricePlan {

    private String code;
    private String name;
    private List<TrafficPlan> trafficPlans;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrafficPlan> getTrafficPlans() {
        return trafficPlans;
    }

    public void setTrafficPlans(List<TrafficPlan> trafficPlans) {
        this.trafficPlans = trafficPlans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PricePlan pricePlan = (PricePlan) o;

        if (code != null ? !code.equals(pricePlan.code) : pricePlan.code != null) return false;
        if (name != null ? !name.equals(pricePlan.name) : pricePlan.name != null) return false;
        if (trafficPlans != null ? !trafficPlans.equals(pricePlan.trafficPlans) : pricePlan.trafficPlans != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (trafficPlans != null ? trafficPlans.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PricePlan{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", trafficPlans=" + trafficPlans +
                '}';
    }
}
