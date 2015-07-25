package com.zy.app.rating.standard.model.buillder;

import com.zy.app.rating.standard.model.TimePlan;
import com.zy.app.rating.standard.model.TimePlanRequest;

import java.time.LocalDateTime;
import java.util.List;


public class TimePlanRequestBuilder {
    List<TimePlan> timePlans;LocalDateTime chargeDate;

    private TimePlanRequestBuilder() {
    }

    public static TimePlanRequestBuilder aTimePlanRequest() {
        return new TimePlanRequestBuilder();
    }

    public TimePlanRequestBuilder withTimePlans(List<TimePlan> timePlans) {
        this.timePlans = timePlans;
        return this;
    }

    public TimePlanRequestBuilder withChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public TimePlanRequestBuilder but() {
        return aTimePlanRequest().withTimePlans(timePlans).withChargeDate(chargeDate);
    }

    public TimePlanRequest build() {
        TimePlanRequest timePlanRequest = new TimePlanRequest();
        timePlanRequest.setTimePlans(timePlans);
        timePlanRequest.setChargeDate(chargeDate);
        return timePlanRequest;
    }
}
