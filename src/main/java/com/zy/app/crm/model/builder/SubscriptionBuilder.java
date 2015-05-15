package com.zy.app.crm.model.builder;

import com.zy.app.crm.model.Subscription;

import java.time.LocalDateTime;

/**
 * aba
 * 26/02/15
 */
public class SubscriptionBuilder {
    int id;
    int userId;LocalDateTime startDate;
    String pricePlanCode;Subscription.Status status;

    private SubscriptionBuilder() {
    }

    public static SubscriptionBuilder aSubscription() {
        return new SubscriptionBuilder();
    }

    public SubscriptionBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public SubscriptionBuilder withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public SubscriptionBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public SubscriptionBuilder withPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
        return this;
    }

    public SubscriptionBuilder withStatus(Subscription.Status status) {
        this.status = status;
        return this;
    }

    public SubscriptionBuilder but() {
        return aSubscription().withId(id).withUserId(userId).withStartDate(startDate).withPricePlanCode(pricePlanCode).withStatus(status);
    }

    public Subscription build() {
        Subscription subscription = new Subscription();
        subscription.setId(id);
        subscription.setUserId(userId);
        subscription.setStartDate(startDate);
        subscription.setPricePlanCode(pricePlanCode);
        subscription.setStatus(status);
        return subscription;
    }
}
