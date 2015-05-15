package com.zy.app.invoice.model.buillder;

import com.zy.app.invoice.model.InvoiceLine;

import java.time.LocalDateTime;


public class InvoiceLineBuilder {
    int id;
    int referenceId;
    int subscriptionId;
    double total;
    String description;
    int invoiceId;
    LocalDateTime chargeDate;

    private InvoiceLineBuilder() {
    }

    public static InvoiceLineBuilder anInvoiceLine() {
        return new InvoiceLineBuilder();
    }

    public InvoiceLineBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public InvoiceLineBuilder withReferenceId(int referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public InvoiceLineBuilder withSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public InvoiceLineBuilder withTotal(double total) {
        this.total = total;
        return this;
    }

    public InvoiceLineBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public InvoiceLineBuilder withInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }

    public InvoiceLineBuilder withChargeDate(LocalDateTime chargeDate) {
        this.chargeDate = chargeDate;
        return this;
    }

    public InvoiceLineBuilder but() {
        return anInvoiceLine().withId(id).withReferenceId(referenceId).withSubscriptionId(subscriptionId).withTotal(total).withDescription(description).withInvoiceId(invoiceId).withChargeDate(chargeDate);
    }

    public InvoiceLine build() {
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setId(id);
        invoiceLine.setReferenceId(referenceId);
        invoiceLine.setSubscriptionId(subscriptionId);
        invoiceLine.setTotal(total);
        invoiceLine.setDescription(description);
        invoiceLine.setInvoiceId(invoiceId);
        invoiceLine.setChargeDate(chargeDate);
        return invoiceLine;
    }
}
