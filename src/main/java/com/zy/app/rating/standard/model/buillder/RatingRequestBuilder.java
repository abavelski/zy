package com.zy.app.rating.standard.model.buillder;

import com.zy.app.rating.standard.model.RatingRequest;

import java.time.LocalDateTime;

/**
 * aba
 * 06/04/15
 */
public class RatingRequestBuilder {
    private String pricePlanCode;
    private String ratingCode;
    private long amount;
    private String destination;
    private LocalDateTime chargeDate;
    private int referenceId;
    private int subscriptionId;

    private RatingRequestBuilder() {
    }

    public static RatingRequestBuilder aRatingRequest() {
        return new RatingRequestBuilder();
    }

    public RatingRequestBuilder withPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
        return this;
    }

    public RatingRequestBuilder withRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
        return this;
    }

    public RatingRequestBuilder withAmount(long amount) {
        this.amount = amount;
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
        return aRatingRequest().withPricePlanCode(pricePlanCode).withRatingCode(ratingCode).withAmount(amount).withDestination(destination).withChargeDate(chargeDate).withReferenceId(referenceId).withSubscriptionId(subscriptionId);
    }

    public RatingRequest build() {
        RatingRequest ratingRequest = new RatingRequest();
        ratingRequest.setPricePlanCode(pricePlanCode);
        ratingRequest.setRatingCode(ratingCode);
        ratingRequest.setAmount(amount);
        ratingRequest.setDestination(destination);
        ratingRequest.setChargeDate(chargeDate);
        ratingRequest.setReferenceId(referenceId);
        ratingRequest.setSubscriptionId(subscriptionId);
        return ratingRequest;
    }
}
