package com.zy.app.rating.model.buillder;

import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.rating.model.RatingCodeRequest;
import com.zy.app.rating.model.TrafficMapping;

import java.util.List;

/**
 * aba
 * 24/03/15
 */
public class RatingCodeRequestBuilder {
    private List<TrafficMapping> mappings;
    private BillingRecord.UsageType usageType;
    private BillingRecord.TrafficType trafficType;

    private RatingCodeRequestBuilder() {
    }

    public static RatingCodeRequestBuilder aRatingCodeRequest() {
        return new RatingCodeRequestBuilder();
    }

    public RatingCodeRequestBuilder withMappings(List<TrafficMapping> mappings) {
        this.mappings = mappings;
        return this;
    }

    public RatingCodeRequestBuilder withUsageType(BillingRecord.UsageType usageType) {
        this.usageType = usageType;
        return this;
    }

    public RatingCodeRequestBuilder withTrafficType(BillingRecord.TrafficType trafficType) {
        this.trafficType = trafficType;
        return this;
    }

    public RatingCodeRequestBuilder but() {
        return aRatingCodeRequest().withMappings(mappings).withUsageType(usageType).withTrafficType(trafficType);
    }

    public RatingCodeRequest build() {
        RatingCodeRequest ratingCodeRequest = new RatingCodeRequest(mappings, usageType, trafficType);
        return ratingCodeRequest;
    }
}
