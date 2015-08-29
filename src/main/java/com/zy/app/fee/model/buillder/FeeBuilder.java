package com.zy.app.fee.model.buillder;

import com.zy.app.fee.model.Fee;

/**
 * alexei.bavelski@gmail.com
 * 29/08/15
 */
public class FeeBuilder {
    String code;
    String description;Fee.Type type;
    double amount;Fee.Period period;

    private FeeBuilder() {
    }

    public static FeeBuilder aFee() {
        return new FeeBuilder();
    }

    public FeeBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public FeeBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public FeeBuilder withType(Fee.Type type) {
        this.type = type;
        return this;
    }

    public FeeBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public FeeBuilder withPeriod(Fee.Period period) {
        this.period = period;
        return this;
    }

    public FeeBuilder but() {
        return aFee().withCode(code).withDescription(description).withType(type).withAmount(amount).withPeriod(period);
    }

    public Fee build() {
        Fee fee = new Fee();
        fee.setCode(code);
        fee.setDescription(description);
        fee.setType(type);
        fee.setAmount(amount);
        fee.setPeriod(period);
        return fee;
    }
}
