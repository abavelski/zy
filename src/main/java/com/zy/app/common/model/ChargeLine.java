package com.zy.app.common.model;


import java.time.LocalDateTime;

public class ChargeLine {
    int subscriptionId;
    double total;
    LocalDateTime chargeDate;
    String description;

    public LocalDateTime getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChargeLine that = (ChargeLine) o;

        if (subscriptionId != that.subscriptionId) return false;
        if (Double.compare(that.total, total) != 0) return false;
        if (chargeDate != null ? !chargeDate.equals(that.chargeDate) : that.chargeDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = subscriptionId;
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (chargeDate != null ? chargeDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChargeLine{" +
                "subscriptionId=" + subscriptionId +
                ", total=" + total +
                ", chargeDate=" + chargeDate +
                ", description='" + description + '\'' +
                '}';
    }
}
