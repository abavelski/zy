package com.zy.rest.invoice;

import com.wordnik.swagger.annotations.Api;
import com.zy.app.crm.main.AccountService;
import com.zy.app.crm.model.Account;
import com.zy.app.invoice.dao.InvoiceDao;
import com.zy.app.invoice.dao.InvoiceLineDao;
import com.zy.app.invoice.main.InvoiceService;
import com.zy.app.invoice.model.Invoice;
import com.zy.app.invoice.model.InvoiceLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * alexei.bavelski@gmail.com
 * 18/07/15
 */

@RestController
@Api(value = "invoice", description = "Invoice operations")
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceLineDao invoiceLineDao;

    @RequestMapping(value="", method = RequestMethod.GET, produces="application/json")
    public List<Invoice> getSortedInvoicesBySubscriptionIdAndStatus(@RequestParam(value="subscriptionId") Integer subscriptionId,
                                                              @RequestParam(value = "status") Invoice.Status status) {
        return invoiceDao.getInvoicesBySubscriptionIdAndStatus(subscriptionId, status);
    }

    @RequestMapping(value = "/{invoiceId}/lines", method = RequestMethod.GET, produces = "application/json")
    public List<InvoiceLine> getInvoiceLinesForInvoice(@PathVariable Integer invoiceId) {
        return invoiceLineDao.getInvoiceLinesForInvoice(invoiceId);
    }


}
