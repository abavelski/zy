package com.zy.app.rating.model.buillder;

import com.zy.app.rating.model.PricePlan;
import com.zy.app.rating.model.TrafficPlan;

import java.util.List;

/**
 * aba
 * 26/02/15
 */
public class PricePlanBuilder {
    private String code;
    private String name;
    private List<TrafficPlan> trafficPlans;

    private PricePlanBuilder() {
    }

    public static PricePlanBuilder aPricePlan() {
        return new PricePlanBuilder();
    }

    public PricePlanBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public PricePlanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PricePlanBuilder withTrafficPlans(List<TrafficPlan> trafficPlans) {
        this.trafficPlans = trafficPlans;
        return this;
    }

    public PricePlanBuilder but() {
        return aPricePlan().withCode(code).withName(name).withTrafficPlans(trafficPlans);
    }

    public PricePlan build() {
        PricePlan pricePlan = new PricePlan();
        pricePlan.setCode(code);
        pricePlan.setName(name);
        pricePlan.setTrafficPlans(trafficPlans);
        return pricePlan;
    }
}
