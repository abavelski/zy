package com.zy.app.rating.model;

import com.zy.app.cdr.model.BillingRecord;

import java.util.List;

public class RatingCodeRequest {
    private final List<TrafficMapping> mappings;
    private final BillingRecord.UsageType usageType;
    private final BillingRecord.TrafficType trafficType;

    public RatingCodeRequest(List<TrafficMapping> mappings, BillingRecord.UsageType usageType, BillingRecord.TrafficType trafficType) {
        this.mappings = mappings;
        this.usageType = usageType;
        this.trafficType = trafficType;
    }

    public List<TrafficMapping> getMappings() {
        return mappings;
    }

    public BillingRecord.UsageType getUsageType() {
        return usageType;
    }

    public BillingRecord.TrafficType getTrafficType() {
        return trafficType;
    }
}
