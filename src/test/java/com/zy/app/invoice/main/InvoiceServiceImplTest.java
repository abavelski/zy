package com.zy.app.invoice.main;


import com.zy.app.common.model.ChargeLine;
import com.zy.app.invoice.dao.InvoiceDao;
import com.zy.app.invoice.dao.InvoiceLineDao;
import com.zy.app.invoice.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static com.zy.app.invoice.main.InvoiceTestData.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceImplTest {
    @Mock
    InvoiceDao invoiceDao;
    @Mock
    InvoiceLineDao invoiceLineDao;
    @Mock
    VatService vatService;
    @Mock
    InvoiceScheduleService scheduleService;
    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Before
    public void setUp() throws Exception {
        when(vatService.calculateVat(60d)).thenReturn(12d);
        when(vatService.calculateVat(10d)).thenReturn(2d);
    }

    @Test
    public void testAddToExistingInvoice() throws Exception {
        //given
        when(invoiceDao.getInvoicesBySubscriptionIdAndStatus(1, Invoice.Status.OPEN)).thenReturn(Arrays.asList(savedInvoice().build()));

        Invoice updatedInvoice = savedInvoice().but()
                .withTotalVat(12d)
                .withTotalExclVat(60d)
                .build();

        //verify
        invoiceService.addChargeToInvoice(newChargeLine().build());
        verify(invoiceLineDao).createInvoiceLine(newInvoiceLine());
        verify(invoiceDao).updateInvoice(updatedInvoice);
    }

    @Test
    public void testAddToNewInvoice() throws Exception {
        //given
        when(invoiceDao.getInvoicesBySubscriptionIdAndStatus(1, Invoice.Status.OPEN)).thenReturn(new ArrayList<>());
        when(invoiceDao.createInvoice(any(Invoice.class))).thenReturn(1);
        when(scheduleService.getScheduleFor(InvoiceTestData.MIDFEBRUARY)).thenReturn(februaryInvoiceSchedule());

        Invoice updatedInvoice = savedInvoice().but()
                .withTotalVat(2d)
                .withTotalExclVat(10d)
                .build();

        //verify
        invoiceService.addChargeToInvoice(newChargeLine().build());
        verify(scheduleService).getScheduleFor(InvoiceTestData.MIDFEBRUARY);
        verify(invoiceDao, times(1)).createInvoice(any(Invoice.class));
        verify(invoiceLineDao).createInvoiceLine(newInvoiceLine());
        verify(invoiceDao).updateInvoice(updatedInvoice);
    }

    @Test
    public void testAddToExistingInvoiceOutsideOfOpenDates() throws Exception {
        //given
        when(invoiceDao.getInvoicesBySubscriptionIdAndStatus(1, Invoice.Status.OPEN)).thenReturn(Arrays.asList(savedInvoice().build()));

        Invoice updatedInvoice = savedInvoice().but()
                .withTotalVat(12d)
                .withTotalExclVat(60d)
                .build();

        ChargeLine chargeLine = newChargeLine().but()
                .withChargeDate(InvoiceTestData.MIDJANUARY)
                .build();

        InvoiceLine invoiceLine = newInvoiceLine();
        invoiceLine.setChargeDate(InvoiceTestData.MIDJANUARY);

        //verify
        invoiceService.addChargeToInvoice(chargeLine);
        verify(invoiceLineDao).createInvoiceLine(invoiceLine);
        verify(invoiceDao).updateInvoice(updatedInvoice);
    }

    @Test
    public void testNewMonthNewInvoice() throws Exception {
        when(invoiceDao.getInvoicesBySubscriptionIdAndStatus(1, Invoice.Status.OPEN)).thenReturn(Arrays.asList(savedInvoice().build()));
        when(invoiceDao.createInvoice(any(Invoice.class))).thenReturn(2);
        when(scheduleService.getScheduleFor(InvoiceTestData.MIDMARCH)).thenReturn(marchInvoiceSchedule());

        ChargeLine chargeLine = newChargeLine().but()
                .withChargeDate(InvoiceTestData.MIDMARCH)
                .build();

        invoiceService.addChargeToInvoice(chargeLine);
        verify(invoiceDao, times(1)).createInvoice(any(Invoice.class));

        Invoice updatedInvoice = savedInvoice().but()
                .withTotalVat(2d)
                .withTotalExclVat(10d)
                .withId(2)
                .withStartDate(STARTMARCH)
                .withEndDate(ENDMARCH)
                .build();
        verify(invoiceDao).updateInvoice(updatedInvoice);

    }
}
