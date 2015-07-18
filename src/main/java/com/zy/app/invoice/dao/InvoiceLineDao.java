package com.zy.app.invoice.dao;

import com.zy.app.invoice.model.InvoiceLine;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */
public interface InvoiceLineDao {
    int createInvoiceLine(InvoiceLine invoiceLine);
}
