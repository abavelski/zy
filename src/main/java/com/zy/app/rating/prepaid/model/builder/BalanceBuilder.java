package com.zy.app.rating.prepaid.model.builder;

import com.zy.app.rating.prepaid.model.Balance;

/**
 * alexei.bavelski@gmail.com
 * 11/09/15
 */
public class BalanceBuilder {
    private int id;
    private int subscriptionId;
    private double amount;
    private double reservedAmount;

    private BalanceBuilder() {
    }

    public static BalanceBuilder aBalance() {
        return new BalanceBuilder();
    }

    public BalanceBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public BalanceBuilder withSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public BalanceBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public BalanceBuilder withReservedAmount(double reservedAmount) {
        this.reservedAmount = reservedAmount;
        return this;
    }

    public BalanceBuilder but() {
        return aBalance().withId(id).withSubscriptionId(subscriptionId).withAmount(amount).withReservedAmount(reservedAmount);
    }

    public Balance build() {
        Balance balance = new Balance();
        balance.setId(id);
        balance.setSubscriptionId(subscriptionId);
        balance.setAmount(amount);
        balance.setReservedAmount(reservedAmount);
        return balance;
    }
}
