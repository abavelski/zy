package com.zy.app.invoice.main;

import com.zy.app.cdr.dao.BillingRecordDao;
import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.common.model.ChargeLine;
import com.zy.app.invoice.dao.InvoiceDao;
import com.zy.app.invoice.dao.InvoiceLineDao;
import com.zy.app.invoice.model.Invoice;
import com.zy.app.invoice.model.InvoiceLine;
import com.zy.app.invoice.model.InvoiceSchedule;
import com.zy.app.rating.standard.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.zy.app.invoice.model.buillder.InvoiceBuilder.anInvoice;
import static com.zy.app.invoice.model.buillder.InvoiceLineBuilder.anInvoiceLine;

@Component
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceLineDao invoiceLineDao;
    @Autowired
    VatService vatService;
    @Autowired
    InvoiceScheduleService scheduleService;
    @Autowired
    BillingRecordDao billingRecordDao;

    @Override
    @Transactional
    public void addChargeToInvoice(ChargeLine chargeLine) {
        List<Invoice> invoices = invoiceDao.getInvoicesBySubscriptionIdAndStatus(chargeLine.getSubscriptionId(), Invoice.Status.OPEN);
        boolean addedCharge = false;
        for (Invoice invoice : invoices) {
           if(isInvoiceOpenOn(invoice, chargeLine.getChargeDate())) {
               createInvoiceLineAndUpdateInvoice(chargeLine, invoice);
               addedCharge = true;
               break;
           }
        }
        if (!addedCharge) {
            if (invoices.size()>0) {
                Invoice invoice = getLastOpenInvoice(invoices);
                createInvoiceLineAndUpdateInvoice(chargeLine, invoice);
            } else {
                Invoice invoice = createNewInvoice(chargeLine);
                createInvoiceLineAndUpdateInvoice(chargeLine, invoice);
            }
        }

    }

    @Override
    @Transactional
    public void updateInvoiceAndBillingRecord(RatingResponse response, BillingRecord br) {
        List<ChargeLine> chargeLines = response.getChargeLines();
        chargeLines.forEach(this::addChargeToInvoice);
        br.setStatus(BillingRecord.Status.RATED);
        billingRecordDao.updateBillingRecord(br);
    }


    private void createInvoiceLineAndUpdateInvoice(ChargeLine chargeLine, Invoice invoice) {
        createInvoiceLine(invoice, chargeLine);
        updateInvoice(invoice, chargeLine);
    }

    private Invoice getLastOpenInvoice(List<Invoice> invoices) {
        return invoices.get(invoices.size()-1);
    }

    private Invoice createNewInvoice(ChargeLine chargeLine) {
        InvoiceSchedule schedule = scheduleService.getScheduleFor(chargeLine.getChargeDate());
        Invoice invoice = anInvoice()
                .withSubscriptionId(chargeLine.getSubscriptionId())
                .withStartDate(schedule.getStartDate())
                .withEndDate(schedule.getEndDate())
                .withStatus(Invoice.Status.OPEN)
                .build();
        int id = invoiceDao.createInvoice(invoice);
        invoice.setId(id);
        return invoice;
    }

    private void updateInvoice(Invoice invoice, ChargeLine chargeLine) {
        double totalExclVat = invoice.getTotalExclVat();
        totalExclVat += chargeLine.getTotal();
        double vat = vatService.calculateVat(totalExclVat);
        invoice.setTotalExclVat(totalExclVat);
        invoice.setTotalVat(vat);
        invoiceDao.updateInvoice(invoice);
    }

    private void createInvoiceLine(Invoice invoice, ChargeLine chargeLine) {
        InvoiceLine invoiceLine = anInvoiceLine()
                .withSubscriptionId(chargeLine.getSubscriptionId())
                .withDescription(chargeLine.getDescription())
                .withChargeDate(chargeLine.getChargeDate())
                .withTotal(chargeLine.getTotal())
                .withInvoiceId(invoice.getId())
                .build();
        invoiceLineDao.createInvoiceLine(invoiceLine);
    }

    private boolean isInvoiceOpenOn(Invoice invoice, LocalDateTime chargeDate) {
        return Invoice.Status.OPEN.equals(invoice.getStatus())
                && chargeDate.isAfter (invoice.getStartDate().atStartOfDay())
                && chargeDate.isBefore(invoice.getEndDate().atStartOfDay());
    }
}
