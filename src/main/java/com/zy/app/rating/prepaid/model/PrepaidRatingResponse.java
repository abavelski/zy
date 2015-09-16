package com.zy.app.rating.prepaid.model;

/**
 * alexei.bavelski@gmail.com
 * 12/09/15
 */
public class PrepaidRatingResponse {

    PrepaidRatingStatus status;
    long grantedUnits;
    double remainingBalance;

    public PrepaidRatingStatus getStatus() {
        return status;
    }

    public void setStatus(PrepaidRatingStatus status) {
        this.status = status;
    }

    public long getGrantedUnits() {
        return grantedUnits;
    }

    public void setGrantedUnits(long grantedUnits) {
        this.grantedUnits = grantedUnits;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrepaidRatingResponse that = (PrepaidRatingResponse) o;

        if (grantedUnits != that.grantedUnits) return false;
        if (Double.compare(that.remainingBalance, remainingBalance) != 0) return false;
        return status == that.status;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = status != null ? status.hashCode() : 0;
        result = 31 * result + (int) (grantedUnits ^ (grantedUnits >>> 32));
        temp = Double.doubleToLongBits(remainingBalance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "RatingPluginResponse{" +
                "status=" + status +
                ", grantedUnits=" + grantedUnits +
                ", remainingBalance=" + remainingBalance +
                '}';
    }
}
