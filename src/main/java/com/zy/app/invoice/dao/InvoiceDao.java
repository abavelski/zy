package com.zy.app.invoice.dao;


import com.zy.app.invoice.model.Invoice;
import com.zy.app.invoice.model.InvoiceLine;

import java.util.List;

public interface InvoiceDao {

    int createInvoice(Invoice invoice);
    void updateInvoice(Invoice invoice);
    List<Invoice> getSortedOpenInvoices(int subscriptionId);


}
