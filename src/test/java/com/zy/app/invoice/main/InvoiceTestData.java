package com.zy.app.invoice.main;

import com.zy.app.common.model.ChargeLine;
import com.zy.app.invoice.model.Invoice;
import com.zy.app.invoice.model.InvoiceLine;
import com.zy.app.invoice.model.InvoiceSchedule;
import com.zy.app.invoice.model.buillder.InvoiceScheduleBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static com.zy.app.common.model.builder.ChargeLineBuilder.aChargeLine;
import static com.zy.app.invoice.model.buillder.InvoiceBuilder.anInvoice;
import static com.zy.app.invoice.model.buillder.InvoiceLineBuilder.anInvoiceLine;


public class InvoiceTestData {

    public static final LocalDateTime CHARGEDATE = LocalDateTime.of(2015, Month.FEBRUARY, 21, 8, 0);
    public static final LocalDateTime OLDCHARGEDATE = LocalDateTime.of(2015, Month.JANUARY, 21, 8, 0);

    public static final LocalDate INVOICESTART = LocalDate.of(2015, Month.FEBRUARY, 1);
    public static final LocalDate INVOICEEND = LocalDate.of(2015, Month.FEBRUARY, 28);


    public static Invoice newInvoice() {
        return anInvoice()
                .withStatus(Invoice.Status.OPEN)
                .withSubscriptionId(1)
                .withStartDate(INVOICESTART)
                .withEndDate(INVOICEEND)
                .build();
    }

    public static Invoice savedInvoice() {
        Invoice invoice = newInvoice();
        invoice.setTotalExclVat(50d);
        invoice.setTotalVat(10d);
        invoice.setId(1);
        return invoice;
    }


    public static ChargeLine newChargeLine() {
        return aChargeLine()
                .withSubscriptionId(1)
                .withChargeDate(CHARGEDATE)
                .withDescription("test")
                .withTotal(10d)
                .build();
    }

    public static InvoiceLine newInvoiceLine() {
        return anInvoiceLine()
                .withSubscriptionId(1)
                .withDescription("test")
                .withTotal(10d)
                .withChargeDate(CHARGEDATE)
                .withInvoiceId(1)
                .build();
    }

    public static InvoiceSchedule newInvoiceSchedule() {
        return new InvoiceScheduleBuilder()
                .withStartDate(INVOICESTART)
                .withEndDate(INVOICEEND)
                .withDescription("test")
                .build();
    }

}
