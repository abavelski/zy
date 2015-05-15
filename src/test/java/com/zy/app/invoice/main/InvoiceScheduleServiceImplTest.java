package com.zy.app.invoice.main;

import com.zy.app.invoice.model.InvoiceSchedule;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InvoiceScheduleServiceImplTest {

    InvoiceScheduleServiceImpl invoiceScheduleService;

    private static  final LocalDateTime CHARGEDATE = LocalDateTime.of(2015, Month.FEBRUARY, 12, 18, 0);
    private static  final LocalDate STARTDATE = LocalDate.of(2015, Month.FEBRUARY, 1);
    private static  final LocalDate ENDDATE = LocalDate.of(2015, Month.MARCH, 1);

    @Test
    public void testGetScheduleFor() throws Exception {

        invoiceScheduleService = new InvoiceScheduleServiceImpl();

        InvoiceSchedule invoiceSchedule = invoiceScheduleService.getScheduleFor(CHARGEDATE);
        assertThat(invoiceSchedule.getStartDate(), equalTo(STARTDATE));
        assertThat(invoiceSchedule.getEndDate(), equalTo(ENDDATE));
        assertThat(invoiceSchedule.getDescription(), equalTo("February Invoice"));
    }
}