package com.zy.app.rating.model;

import java.time.LocalDateTime;
import java.util.List;

public class TimePlanRequest {
    List<TimePlan> timePlans;
    LocalDateTime chargeDate;

    public List<TimePlan> getTimePlans() {
        return timePlans;
    }

    public void setTimePlans(List<TimePlan> timePlans) {
        this.timePlans = timePlans;
    }

    public LocalDateTime getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
    }
}
