package com.zy.app.invoice.model.buillder;

import com.zy.app.invoice.model.InvoiceSchedule;

import java.time.LocalDate;

public class InvoiceScheduleBuilder {

    private LocalDate startDate;
    private LocalDate endDate;
    private String description;


    public InvoiceScheduleBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public InvoiceScheduleBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public InvoiceScheduleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public InvoiceSchedule build() {
        InvoiceSchedule invoiceSchedule = new InvoiceSchedule();
        invoiceSchedule.setStartDate(startDate);
        invoiceSchedule.setEndDate(endDate);
        invoiceSchedule.setDescription(description);
        return invoiceSchedule;
    }
}