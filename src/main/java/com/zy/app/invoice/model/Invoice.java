package com.zy.app.invoice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Invoice {

    public enum Status {OPEN, CLOSED}

    int id;
    int subscriptionId;
    LocalDate startDate;
    LocalDate endDate;
    LocalDateTime closeDate;
    Status status;
    double totalExclVat;
    double totalVat;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotalExclVat() {
        return totalExclVat;
    }

    public void setTotalExclVat(double totalExclVat) {
        this.totalExclVat = totalExclVat;
    }

    public double getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(double totalVat) {
        this.totalVat = totalVat;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (id != invoice.id) return false;
        if (subscriptionId != invoice.subscriptionId) return false;
        if (Double.compare(invoice.totalExclVat, totalExclVat) != 0) return false;
        if (Double.compare(invoice.totalVat, totalVat) != 0) return false;
        if (closeDate != null ? !closeDate.equals(invoice.closeDate) : invoice.closeDate != null) return false;
        if (endDate != null ? !endDate.equals(invoice.endDate) : invoice.endDate != null) return false;
        if (startDate != null ? !startDate.equals(invoice.startDate) : invoice.startDate != null) return false;
        if (status != invoice.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + subscriptionId;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (closeDate != null ? closeDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        temp = Double.doubleToLongBits(totalExclVat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalVat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", subscriptionId=" + subscriptionId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", closeDate=" + closeDate +
                ", status=" + status +
                ", totalExclVat=" + totalExclVat +
                ", totalVat=" + totalVat +
                '}';
    }
}
