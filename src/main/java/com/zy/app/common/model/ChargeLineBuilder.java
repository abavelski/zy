package com.zy.app.common.model;

import java.time.LocalDateTime;

/**
 * aba
 * 26/02/15
 */
public class ChargeLineBuilder {
    Integer subscriptionId;
    double total;LocalDateTime chargeDate;
    String description;

    private ChargeLineBuilder() {
    }

    public static ChargeLineBuilder aChargeLine() {
        return new ChargeLineBuilder();
    }

    public ChargeLineBuilder withSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public ChargeLineBuilder withTotal(double total) {
        this.total = total;
        return this;
    }

    public ChargeLineBuilder withChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public ChargeLineBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ChargeLineBuilder but() {
        return aChargeLine().withSubscriptionId(subscriptionId).withTotal(total).withChargeDate(chargeDate).withDescription(description);
    }

    public ChargeLine build() {
        ChargeLine chargeLine = new ChargeLine();
        chargeLine.setSubscriptionId(subscriptionId);
        chargeLine.setTotal(total);
        chargeLine.setChargeDate(chargeDate);
        chargeLine.setDescription(description);
        return chargeLine;
    }
}
