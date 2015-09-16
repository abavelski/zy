package com.zy.app.rating.standard.model;


import java.time.LocalDateTime;

/**
 * User: aba
 * Date: 10/22/12
 */

public class RatingRequest {

    private String sessionKey;
    private String pricePlanCode;
    private String ratingCode;
    private long units;
    private String destination;
    private LocalDateTime chargeDate;
    private int referenceId;
    private int subscriptionId;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public long getUnits() {
        return units;
    }

    public void setUnits(long units) {
        this.units = units;
    }

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

        if (units != that.units) return false;
        if (referenceId != that.referenceId) return false;
        if (subscriptionId != that.subscriptionId) return false;
        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;
        if (pricePlanCode != null ? !pricePlanCode.equals(that.pricePlanCode) : that.pricePlanCode != null)
            return false;
        if (ratingCode != null ? !ratingCode.equals(that.ratingCode) : that.ratingCode != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        return !(chargeDate != null ? !chargeDate.equals(that.chargeDate) : that.chargeDate != null);

    }

    @Override
    public int hashCode() {
        int result = sessionKey != null ? sessionKey.hashCode() : 0;
        result = 31 * result + (pricePlanCode != null ? pricePlanCode.hashCode() : 0);
        result = 31 * result + (ratingCode != null ? ratingCode.hashCode() : 0);
        result = 31 * result + (int) (units ^ (units >>> 32));
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (chargeDate != null ? chargeDate.hashCode() : 0);
        result = 31 * result + referenceId;
        result = 31 * result + subscriptionId;
        return result;
    }

    @Override
    public String toString() {
        return "RatingRequest{" +
                "sessionKey='" + sessionKey + '\'' +
                ", pricePlanCode='" + pricePlanCode + '\'' +
                ", ratingCode='" + ratingCode + '\'' +
                ", units=" + units +
                ", destination='" + destination + '\'' +
                ", chargeDate=" + chargeDate +
                ", referenceId=" + referenceId +
                ", subscriptionId=" + subscriptionId +
                '}';
    }
}
