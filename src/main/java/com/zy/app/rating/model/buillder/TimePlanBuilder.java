package com.zy.app.rating.model.buillder;

import com.zy.app.rating.model.Charge;
import com.zy.app.rating.model.TimePlan;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 20/07/15
 */
public class TimePlanBuilder {
    private String code;
    private Integer startHour;
    private Integer endHour;
    private List<Charge> charges;
    private List<String> campaigns;

    private TimePlanBuilder() {
    }

    public static TimePlanBuilder aTimePlan() {
        return new TimePlanBuilder();
    }

    public TimePlanBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public TimePlanBuilder withStartHour(Integer startHour) {
        this.startHour = startHour;
        return this;
    }

    public TimePlanBuilder withEndHour(Integer endHour) {
        this.endHour = endHour;
        return this;
    }

    public TimePlanBuilder withCharges(List<Charge> charges) {
        this.charges = charges;
        return this;
    }

    public TimePlanBuilder withCampaigns(List<String> campaigns) {
        this.campaigns = campaigns;
        return this;
    }

    public TimePlanBuilder but() {
        return aTimePlan().withCode(code).withStartHour(startHour).withEndHour(endHour).withCharges(charges).withCampaigns(campaigns);
    }

    public TimePlan build() {
        TimePlan timePlan = new TimePlan();
        timePlan.setCode(code);
        timePlan.setStartHour(startHour);
        timePlan.setEndHour(endHour);
        timePlan.setCharges(charges);
        timePlan.setCampaigns(campaigns);
        return timePlan;
    }
}
