package com.zy.app.invoice.model.buillder;

import com.zy.app.invoice.model.Invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aba
 * 14/03/15
 */
public class InvoiceBuilder {
    int id;
    int subscriptionId;
    LocalDate startDate;
    LocalDate endDate;
    LocalDateTime closeDate;
    Invoice.Status status;
    double totalExclVat;
    double totalVat;

    private InvoiceBuilder() {
    }

    public static InvoiceBuilder anInvoice() {
        return new InvoiceBuilder();
    }

    public InvoiceBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public InvoiceBuilder withSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public InvoiceBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public InvoiceBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public InvoiceBuilder withCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
        return this;
    }

    public InvoiceBuilder withStatus(Invoice.Status status) {
        this.status = status;
        return this;
    }

    public InvoiceBuilder withTotalExclVat(double totalExclVat) {
        this.totalExclVat = totalExclVat;
        return this;
    }

    public InvoiceBuilder withTotalVat(double totalVat) {
        this.totalVat = totalVat;
        return this;
    }

    public InvoiceBuilder but() {
        return anInvoice().withId(id).withSubscriptionId(subscriptionId).withStartDate(startDate).withEndDate(endDate).withCloseDate(closeDate).withStatus(status).withTotalExclVat(totalExclVat).withTotalVat(totalVat);
    }

    public Invoice build() {
        Invoice invoice = new Invoice();
        invoice.setId(id);
        invoice.setSubscriptionId(subscriptionId);
        invoice.setStartDate(startDate);
        invoice.setEndDate(endDate);
        invoice.setCloseDate(closeDate);
        invoice.setStatus(status);
        invoice.setTotalExclVat(totalExclVat);
        invoice.setTotalVat(totalVat);
        return invoice;
    }
}
