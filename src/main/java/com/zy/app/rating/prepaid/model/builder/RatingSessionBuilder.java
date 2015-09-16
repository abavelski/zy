package com.zy.app.rating.prepaid.model.builder;

import com.zy.app.rating.prepaid.model.RatingSession;

import java.time.LocalDateTime;

/**
 * alexei.bavelski@gmail.com
 * 12/09/15
 */
public class RatingSessionBuilder {
    String sessionKey;
    LocalDateTime chargeDate;
    long usedUnits;
    long reservedUnits;
    double price;

    private RatingSessionBuilder() {
    }

    public static RatingSessionBuilder aRatingSession() {
        return new RatingSessionBuilder();
    }

    public RatingSessionBuilder withSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public RatingSessionBuilder withChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public RatingSessionBuilder withUsedUnits(long usedUnits) {
        this.usedUnits = usedUnits;
        return this;
    }

    public RatingSessionBuilder withReservedUnits(long reservedUnits) {
        this.reservedUnits = reservedUnits;
        return this;
    }

    public RatingSessionBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public RatingSessionBuilder but() {
        return aRatingSession().withSessionKey(sessionKey).withChargeDate(chargeDate).withUsedUnits(usedUnits).withReservedUnits(reservedUnits).withPrice(price);
    }

    public RatingSession build() {
        RatingSession ratingSession = new RatingSession();
        ratingSession.setSessionKey(sessionKey);
        ratingSession.setChargeDate(chargeDate);
        ratingSession.setUsedUnits(usedUnits);
        ratingSession.setReservedUnits(reservedUnits);
        ratingSession.setPrice(price);
        return ratingSession;
    }
}
