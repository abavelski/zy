package com.zy.app.invoice.model;

import java.time.LocalDateTime;

public class InvoiceLine {
    int id;
    int referenceId;
    int subscriptionId;
    double total;
    String description;
    int invoiceId;
    LocalDateTime chargeDate;

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

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoiceLine that = (InvoiceLine) o;

        if (id != that.id) return false;
        if (invoiceId != that.invoiceId) return false;
        if (referenceId != that.referenceId) return false;
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
        result = id;
        result = 31 * result + referenceId;
        result = 31 * result + subscriptionId;
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + invoiceId;
        result = 31 * result + (chargeDate != null ? chargeDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "id=" + id +
                ", referenceId=" + referenceId +
                ", subscriptionId=" + subscriptionId +
                ", total=" + total +
                ", description='" + description + '\'' +
                ", invoiceId=" + invoiceId +
                ", chargeDate=" + chargeDate +
                '}';
    }
}
