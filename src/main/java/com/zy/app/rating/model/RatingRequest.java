package com.zy.app.rating.model;


import java.time.LocalDateTime;

/**
 * User: aba
 * Date: 10/22/12
 */

public class RatingRequest {

    private String pricePlanCode;
    private String ratingCode;
    private long amount;
    private String destination;
    private LocalDateTime chargeDate;
    private int referenceId;
    private int subscriptionId;

    public String getPricePlanCode() {
        return pricePlanCode;
    }

    public void setPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingRequest that = (RatingRequest) o;

        if (amount != that.amount) return false;
        if (referenceId != that.referenceId) return false;
        if (subscriptionId != that.subscriptionId) return false;
        if (chargeDate != null ? !chargeDate.equals(that.chargeDate) : that.chargeDate != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (pricePlanCode != null ? !pricePlanCode.equals(that.pricePlanCode) : that.pricePlanCode != null)
            return false;
        if (ratingCode != null ? !ratingCode.equals(that.ratingCode) : that.ratingCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pricePlanCode != null ? pricePlanCode.hashCode() : 0;
        result = 31 * result + (ratingCode != null ? ratingCode.hashCode() : 0);
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (chargeDate != null ? chargeDate.hashCode() : 0);
        result = 31 * result + referenceId;
        result = 31 * result + subscriptionId;
        return result;
    }

    @Override
    public String toString() {
        return "RatingRequest{" +
                "pricePlanCode='" + pricePlanCode + '\'' +
                ", ratingCode='" + ratingCode + '\'' +
                ", amount=" + amount +
                ", destination='" + destination + '\'' +
                ", chargeDate=" + chargeDate +
                ", referenceId=" + referenceId +
                ", subscriptionId=" + subscriptionId +
                '}';
    }
}
