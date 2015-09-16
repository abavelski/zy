package com.zy.app.rating.prepaid.model;

/**
 * alexei.bavelski@gmail.com
 * 10/09/15
 */
public class Balance {
    private int id;
    private int subscriptionId;
    private double amount;
    private double reservedAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getReservedAmount() {
        return reservedAmount;
    }

    public void setReservedAmount(double reservedAmount) {
        this.reservedAmount = reservedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance = (Balance) o;

        if (id != balance.id) return false;
        if (subscriptionId != balance.subscriptionId) return false;
        if (Math.abs(balance.amount- amount) > 0.0001) return false;
        if (Math.abs(balance.reservedAmount - reservedAmount) > 0.0001) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + subscriptionId;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(reservedAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", subscriptionId=" + subscriptionId +
                ", amount=" + amount +
                ", reservedAmount=" + reservedAmount +
                '}';
    }
}
