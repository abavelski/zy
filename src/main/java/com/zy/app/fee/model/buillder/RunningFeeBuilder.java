package com.zy.app.fee.model.buillder;

import com.zy.app.fee.model.RunningFee;

import java.time.LocalDate;

/**
 * User: alexei.bavelski@gmail.com
 * Date: 24-07-2015
 */
public class RunningFeeBuilder {
    int id;
    String feeCode;
    int subscriptionId;
    LocalDate nextChargeDate;
    RunningFee.Status status;

    private RunningFeeBuilder() {
    }

    public static RunningFeeBuilder aRunningFee() {
        return new RunningFeeBuilder();
    }

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

    public RunningFeeBuilder but() {
        return aRunningFee().withId(id).withFeeCode(feeCode).withSubscriptionId(subscriptionId).withNextChargeDate(nextChargeDate).withStatus(status);
    }

    public RunningFee build() {
        RunningFee runningFee = new RunningFee();
        runningFee.setId(id);
        runningFee.setFeeCode(feeCode);
        runningFee.setSubscriptionId(subscriptionId);
        runningFee.setNextChargeDate(nextChargeDate);
        runningFee.setStatus(status);
        return runningFee;
    }
}
