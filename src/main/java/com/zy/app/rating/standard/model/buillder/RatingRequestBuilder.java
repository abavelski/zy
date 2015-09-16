package com.zy.app.rating.standard.model.buillder;

import com.zy.app.rating.standard.model.RatingRequest;

import java.time.LocalDateTime;

/**
 * alexei.bavelski@gmail.com
 * 11/09/15
 */
public class RatingRequestBuilder {
    private String sessionKey;
    private String pricePlanCode;
    private String ratingCode;
    private long units;
    private String destination;
    private LocalDateTime chargeDate;
    private int referenceId;
    private int subscriptionId;

    private RatingRequestBuilder() {
    }

    public static RatingRequestBuilder aRatingRequest() {
        return new RatingRequestBuilder();
    }

    public RatingRequestBuilder withSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public RatingRequestBuilder withPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
        return this;
    }

    public RatingRequestBuilder withRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
        return this;
    }

    public RatingRequestBuilder withUnits(long units) {
        this.units = units;
        return this;
    }

    public RatingRequestBuilder withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public RatingRequestBuilder withChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public RatingRequestBuilder withReferenceId(int referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public RatingRequestBuilder withSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public RatingRequestBuilder but() {
        return aRatingRequest().withSessionKey(sessionKey).withPricePlanCode(pricePlanCode).withRatingCode(ratingCode).withUnits(units).withDestination(destination).withChargeDate(chargeDate).withReferenceId(referenceId).withSubscriptionId(subscriptionId);
    }

    public RatingRequest build() {
        RatingRequest ratingRequest = new RatingRequest();
        ratingRequest.setSessionKey(sessionKey);
        ratingRequest.setPricePlanCode(pricePlanCode);
        ratingRequest.setRatingCode(ratingCode);
        ratingRequest.setUnits(units);
        ratingRequest.setDestination(destination);
        ratingRequest.setChargeDate(chargeDate);
        ratingRequest.setReferenceId(referenceId);
        ratingRequest.setSubscriptionId(subscriptionId);
        return ratingRequest;
    }
}
