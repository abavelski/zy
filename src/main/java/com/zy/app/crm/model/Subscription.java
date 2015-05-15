package com.zy.app.crm.model;

import java.time.LocalDateTime;


public class Subscription {
    public enum Status {INITIAL, ACTIVE, TERMINATED }

    int id;
    int userId;
    LocalDateTime startDate;
    String pricePlanCode;
    Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPricePlanCode() {
        return pricePlanCode;
    }

    public void setPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (pricePlanCode != null ? !pricePlanCode.equals(that.pricePlanCode) : that.pricePlanCode != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (status != that.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (pricePlanCode != null ? pricePlanCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
