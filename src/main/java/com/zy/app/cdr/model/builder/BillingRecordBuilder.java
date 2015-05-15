package com.zy.app.cdr.model.builder;

import com.zy.app.cdr.model.BillingRecord;

import java.time.LocalDateTime;

/**
 * aba
 * 05/04/15
 */
public class BillingRecordBuilder {
    int id;
    int phoneNumber;
    String destination;
    long amount;
    LocalDateTime chargeDate;
    BillingRecord.UsageType usageType;
    BillingRecord.TrafficType trafficType;
    BillingRecord.Status status;

    private BillingRecordBuilder() {
    }

    public static BillingRecordBuilder aBillingRecord() {
        return new BillingRecordBuilder();
    }

    public BillingRecordBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public BillingRecordBuilder withPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public BillingRecordBuilder withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public BillingRecordBuilder withAmount(long amount) {
        this.amount = amount;
        return this;
    }

    public BillingRecordBuilder withChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public BillingRecordBuilder withUsageType(BillingRecord.UsageType usageType) {
        this.usageType = usageType;
        return this;
    }

    public BillingRecordBuilder withTrafficType(BillingRecord.TrafficType trafficType) {
        this.trafficType = trafficType;
        return this;
    }

    public BillingRecordBuilder withStatus(BillingRecord.Status status) {
        this.status = status;
        return this;
    }

    public BillingRecordBuilder but() {
        return aBillingRecord().withId(id).withPhoneNumber(phoneNumber).withDestination(destination).withAmount(amount).withChargeDate(chargeDate).withUsageType(usageType).withTrafficType(trafficType).withStatus(status);
    }

    public BillingRecord build() {
        BillingRecord billingRecord = new BillingRecord();
        billingRecord.setId(id);
        billingRecord.setPhoneNumber(phoneNumber);
        billingRecord.setDestination(destination);
        billingRecord.setAmount(amount);
        billingRecord.setChargeDate(chargeDate);
        billingRecord.setUsageType(usageType);
        billingRecord.setTrafficType(trafficType);
        billingRecord.setStatus(status);
        return billingRecord;
    }
}
