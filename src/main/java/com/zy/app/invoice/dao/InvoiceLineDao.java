package com.zy.app.invoice.dao;

import com.zy.app.invoice.model.InvoiceLine;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */
public interface InvoiceLineDao {
    int createInvoiceLine(InvoiceLine invoiceLine);
    List<InvoiceLine> getInvoiceLinesForInvoice(Integer invoiceId);
}
