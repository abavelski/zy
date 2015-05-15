package com.zy.app.rating.model.buillder;

import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.rating.model.TrafficMapping;

/**
 * aba
 * 23/03/15
 */
public class TrafficMappingBuilder {
    BillingRecord.TrafficType trafficType;
    BillingRecord.UsageType usageType;
    String ratingCode;

    private TrafficMappingBuilder() {
    }

    public static TrafficMappingBuilder aTrafficMapping() {
        return new TrafficMappingBuilder();
    }

    public TrafficMappingBuilder withTrafficType(BillingRecord.TrafficType trafficType) {
        this.trafficType = trafficType;
        return this;
    }

    public TrafficMappingBuilder withUsageType(BillingRecord.UsageType usageType) {
        this.usageType = usageType;
        return this;
    }

    public TrafficMappingBuilder withRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
        return this;
    }

    public TrafficMappingBuilder but() {
        return aTrafficMapping().withTrafficType(trafficType).withUsageType(usageType).withRatingCode(ratingCode);
    }

    public TrafficMapping build() {
        TrafficMapping trafficMapping = new TrafficMapping();
        trafficMapping.setTrafficType(trafficType);
        trafficMapping.setUsageType(usageType);
        trafficMapping.setRatingCode(ratingCode);
        return trafficMapping;
    }
}
