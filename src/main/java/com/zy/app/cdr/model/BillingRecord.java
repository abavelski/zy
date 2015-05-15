package com.zy.app.cdr.model;

import java.time.LocalDateTime;

/**
 * aba
 * 22/03/15
 */
public class BillingRecord {

    public enum UsageType {VOICE, DATA, SMS, MMS}
    public enum TrafficType {HOME, INT, ROAM_IN, ROAM_OUT}
    public enum Status {INITIAL, RATED, ERROR}

    int id;
    int phoneNumber;
    String destination;
    long amount;
    LocalDateTime chargeDate;
    UsageType usageType;
    TrafficType trafficType;
    Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public TrafficType getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(TrafficType trafficType) {
        this.trafficType = trafficType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
