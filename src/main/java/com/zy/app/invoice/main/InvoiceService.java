package com.zy.app.invoice.main;


import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.common.model.ChargeLine;
import com.zy.app.invoice.model.Invoice;
import com.zy.app.invoice.model.InvoiceSchedule;
import com.zy.app.rating.standard.model.RatingResponse;

import java.util.List;

public interface InvoiceService {

    void addChargeToInvoice(ChargeLine chargeLine);
    void updateInvoiceAndBillingRecord(RatingResponse response, BillingRecord br);
    List<Invoice> getInvoicesForScheduleAndStatus(InvoiceSchedule schedule, Invoice.Status status);

}
