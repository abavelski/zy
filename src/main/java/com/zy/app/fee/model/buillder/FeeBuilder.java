package com.zy.app.fee.model.buillder;

import com.zy.app.fee.model.Fee;

public class FeeBuilder {
    private String code;
    private String description;
    private Fee.Type type;
    private double amount;
    private Fee.Period period;

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


    public Fee build() {
        Fee fee = new Fee();
        fee.setDescription(description);
        fee.setType(type);
        fee.setPeriod(period);
        fee.setAmount(amount);
        fee.setCode(code);
        return fee;
    }
}