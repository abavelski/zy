package com.zy.app.rating.model;

import com.zy.app.cdr.model.BillingRecord;

/**
 * aba
 * 23/03/15
 */
public class TrafficMapping {

    BillingRecord.TrafficType trafficType;
    BillingRecord.UsageType usageType;
    String ratingCode;

    public BillingRecord.TrafficType getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(BillingRecord.TrafficType trafficType) {
        this.trafficType = trafficType;
    }

    public BillingRecord.UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(BillingRecord.UsageType usageType) {
        this.usageType = usageType;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
    }
}
