package com.zy.app.invoice.main;


import com.zy.app.common.model.ChargeLine;
import com.zy.app.invoice.dao.InvoiceDao;
import com.zy.app.invoice.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceImplTest {
    @Mock
    InvoiceDao invoiceDao;
    @Mock
    VatService vatService;
    @Mock
    InvoiceScheduleService scheduleService;
    @InjectMocks
    InvoiceServiceImpl invoiceService;



    @Test
    public void testAddToExistingInvoice() throws Exception {
        //given
        when(invoiceDao.getSortedOpenInvoices(1)).thenReturn(Arrays.asList(InvoiceTestData.savedInvoice()));
        when(vatService.calculateVat(60d)).thenReturn(12d);

        Invoice updatedInvoice = InvoiceTestData.savedInvoice();
        updatedInvoice.setTotalVat(12d);
        updatedInvoice.setTotalExclVat(60d);
        //verify
        invoiceService.addChargeToInvoice(InvoiceTestData.newChargeLine());
        verify(invoiceDao).createInvoiceLine(InvoiceTestData.newInvoiceLine());
        verify(invoiceDao).updateInvoice(updatedInvoice);
    }

    @Test
    public void testAddToNewInvoice() throws Exception {
        //given
        when(invoiceDao.getSortedOpenInvoices(1)).thenReturn(new ArrayList<>());
        when(invoiceDao.createInvoice(any(Invoice.class))).thenReturn(1);
        when(vatService.calculateVat(10d)).thenReturn(2d);
        when(scheduleService.getScheduleFor(InvoiceTestData.CHARGEDATE)).thenReturn(InvoiceTestData.newInvoiceSchedule());

        Invoice updatedInvoice = InvoiceTestData.savedInvoice();
        updatedInvoice.setTotalVat(2d);
        updatedInvoice.setTotalExclVat(10d);

        //verify
        invoiceService.addChargeToInvoice(InvoiceTestData.newChargeLine());
        verify(scheduleService).getScheduleFor(InvoiceTestData.CHARGEDATE);
        verify(invoiceDao, times(1)).createInvoice(any(Invoice.class));
        verify(invoiceDao).createInvoiceLine(InvoiceTestData.newInvoiceLine());
        verify(invoiceDao).updateInvoice(updatedInvoice);
    }

    @Test
    public void testAddToExistingInvoiceOutsideOfOpenDates() throws Exception {
        //given
        when(invoiceDao.getSortedOpenInvoices(1)).thenReturn(Arrays.asList(InvoiceTestData.savedInvoice()));
        when(vatService.calculateVat(60d)).thenReturn(12d);

        Invoice updatedInvoice = InvoiceTestData.savedInvoice();
        updatedInvoice.setTotalVat(12d);
        updatedInvoice.setTotalExclVat(60d);

        ChargeLine chargeLine = InvoiceTestData.newChargeLine();
        chargeLine.setChargeDate(InvoiceTestData.OLDCHARGEDATE);

        InvoiceLine invoiceLine = InvoiceTestData.newInvoiceLine();
        invoiceLine.setChargeDate(InvoiceTestData.OLDCHARGEDATE);

        //verify
        invoiceService.addChargeToInvoice(chargeLine);
        verify(invoiceDao).createInvoiceLine(invoiceLine);
        verify(invoiceDao).updateInvoice(updatedInvoice);
    }
}
