package com.zy.app.fee.model.buillder;

import com.zy.app.fee.model.RunningFee;

import java.time.LocalDate;

public class RunningFeeBuilder {
    private int id;
    private String feeCode;
    private int subscriptionId;
    private LocalDate nextChargeDate;
    private RunningFee.Status status;

    public RunningFeeBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public RunningFeeBuilder withFeeCode(String feeCode) {
        this.feeCode = feeCode;
        return this;
    }

    public RunningFeeBuilder withSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public RunningFeeBuilder withNextChargeDate(LocalDate nextChargeDate) {
        this.nextChargeDate = nextChargeDate;
        return this;
    }

    public RunningFeeBuilder withStatus(RunningFee.Status status) {
        this.status = status;
        return this;
    }

    public RunningFee build() {
        RunningFee runningFee = new RunningFee();
        runningFee.setStatus(status);
        runningFee.setSubscriptionId(subscriptionId);
        runningFee.setId(id);
        runningFee.setFeeCode(feeCode);
        runningFee.setNextChargeDate(nextChargeDate);
        return runningFee;
    }
}