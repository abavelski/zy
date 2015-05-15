package com.zy.app.crm.model;


public class Service {
    public enum Status {INITIAL, ACTIVE, TERMINATED }

    int id;
    int subscriptionId;
    Integer phoneNumber;
    Status status;

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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (id != service.id) return false;
        if (subscriptionId != service.subscriptionId) return false;
        if (phoneNumber != null ? !phoneNumber.equals(service.phoneNumber) : service.phoneNumber != null) return false;
        if (status != service.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + subscriptionId;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", subscriptionId=" + subscriptionId +
                ", phoneNumber=" + phoneNumber +
                ", status=" + status +
                '}';
    }
}
