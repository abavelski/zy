package com.zy.app.rating.prepaid.model;

import java.time.LocalDateTime;

/**
 * alexei.bavelski@gmail.com
 * 11/09/15
 */
public class RatingSession {

    String sessionKey;
    LocalDateTime chargeDate;
    long usedUnits;
    long reservedUnits;
    double price;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public LocalDateTime getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
    }

    public long getUsedUnits() {
        return usedUnits;
    }

    public void setUsedUnits(long usedUnits) {
        this.usedUnits = usedUnits;
    }

    public long getReservedUnits() {
        return reservedUnits;
    }

    public void setReservedUnits(long reservedUnits) {
        this.reservedUnits = reservedUnits;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingSession that = (RatingSession) o;

        if (usedUnits != that.usedUnits) return false;
        if (reservedUnits != that.reservedUnits) return false;
        if (Math.abs(that.price-price) > 0.0001) return false;
        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;
        return !(chargeDate != null ? !chargeDate.equals(that.chargeDate) : that.chargeDate != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = sessionKey != null ? sessionKey.hashCode() : 0;
        result = 31 * result + (chargeDate != null ? chargeDate.hashCode() : 0);
        result = 31 * result + (int) (usedUnits ^ (usedUnits >>> 32));
        result = 31 * result + (int) (reservedUnits ^ (reservedUnits >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "RatingSession{" +
                "sessionKey='" + sessionKey + '\'' +
                ", chargeDate=" + chargeDate +
                ", usedUnits=" + usedUnits +
                ", reservedUnits=" + reservedUnits +
                ", price=" + price +
                '}';
    }
}
