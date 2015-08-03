package com.zy.app.invoice.main;

import com.zy.app.common.model.builder.ChargeLineBuilder;
import com.zy.app.invoice.model.Invoice;
import com.zy.app.invoice.model.InvoiceLine;
import com.zy.app.invoice.model.InvoiceSchedule;
import com.zy.app.invoice.model.buillder.InvoiceBuilder;
import com.zy.app.invoice.model.buillder.InvoiceScheduleBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static com.zy.app.common.model.builder.ChargeLineBuilder.aChargeLine;
import static com.zy.app.invoice.model.buillder.InvoiceBuilder.anInvoice;
import static com.zy.app.invoice.model.buillder.InvoiceLineBuilder.anInvoiceLine;


public class InvoiceTestData {

    public static final LocalDateTime MIDMARCH = LocalDateTime.of(2015, Month.MARCH, 21, 8, 0);
    public static final LocalDateTime MIDFEBRUARY = LocalDateTime.of(2015, Month.FEBRUARY, 21, 8, 0);
    public static final LocalDateTime MIDJANUARY = LocalDateTime.of(2015, Month.JANUARY, 21, 8, 0);

    public static final LocalDate STARTFEB = LocalDate.of(2015, Month.FEBRUARY, 1);
    public static final LocalDate STARTMARCH = LocalDate.of(2015, Month.MARCH, 1);
    public static final LocalDate ENDFEB = LocalDate.of(2015, Month.FEBRUARY, 28);
    public static final LocalDate ENDMARCH = LocalDate.of(2015, Month.MARCH, 31);


    public static Invoice newInvoice() {
        return anInvoice()
                .withStatus(Invoice.Status.OPEN)
                .withSubscriptionId(1)
                .withStartDate(STARTFEB)
                .withEndDate(ENDFEB)
                .build();
    }

    public static InvoiceBuilder savedInvoice() {
        return anInvoice()
                .withTotalExclVat(50d)
                .withStartDate(STARTFEB)
                .withEndDate(ENDFEB)
                .withStatus(Invoice.Status.OPEN)
                .withTotalVat(10d)
                .withSubscriptionId(1)
                .withId(1);
    }


    public static ChargeLineBuilder newChargeLine() {
        return aChargeLine()
                .withSubscriptionId(1)
                .withChargeDate(MIDFEBRUARY)
                .withDescription("test")
                .withTotal(10d);

    }

    public static InvoiceLine newInvoiceLine() {
        return anInvoiceLine()
                .withSubscriptionId(1)
                .withDescription("test")
                .withTotal(10d)
                .withChargeDate(MIDFEBRUARY)
                .withInvoiceId(1)
                .build();
    }

    public static InvoiceSchedule februaryInvoiceSchedule() {
        return new InvoiceScheduleBuilder()
                .withStartDate(STARTFEB)
                .withEndDate(ENDFEB)
                .withDescription("test")
                .build();
    }

    public static InvoiceSchedule marchInvoiceSchedule() {
        return new InvoiceScheduleBuilder()
                .withStartDate(STARTMARCH)
                .withEndDate(ENDMARCH)
                .withDescription("test")
                .build();
    }

}
