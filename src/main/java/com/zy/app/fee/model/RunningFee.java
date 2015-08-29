package com.zy.app.fee.model;


import java.time.LocalDate;

public class RunningFee {

    public enum Status {INITIAL, ACTIVE, TERMINATED}

    int id;
    String feeCode;
    int subscriptionId;
    LocalDate nextChargeDate;
    Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public LocalDate getNextChargeDate() {
        return nextChargeDate;
    }

    public void setNextChargeDate(LocalDate nextChargeDate) {
        this.nextChargeDate = nextChargeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunningFee that = (RunningFee) o;

        if (id != that.id) return false;
        if (subscriptionId != that.subscriptionId) return false;
        if (feeCode != null ? !feeCode.equals(that.feeCode) : that.feeCode != null) return false;
        if (nextChargeDate != null ? !nextChargeDate.equals(that.nextChargeDate) : that.nextChargeDate != null)
            return false;
        if (status != that.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (feeCode != null ? feeCode.hashCode() : 0);
        result = 31 * result + subscriptionId;
        result = 31 * result + (nextChargeDate != null ? nextChargeDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RunningFee{" +
                "id=" + id +
                ", feeCode='" + feeCode + '\'' +
                ", subscriptionId=" + subscriptionId +
                ", nextChargeDate=" + nextChargeDate +
                ", status=" + status +
                '}';
    }
}
