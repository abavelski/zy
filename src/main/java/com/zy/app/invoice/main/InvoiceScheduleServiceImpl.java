package com.zy.app.invoice.main;

import com.zy.app.invoice.model.InvoiceSchedule;
import com.zy.app.invoice.model.buillder.InvoiceScheduleBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class InvoiceScheduleServiceImpl implements InvoiceScheduleService {

    @Override
    public InvoiceSchedule getScheduleFor(LocalDateTime date) {
        int year = date.getYear();
        Month month = date.getMonth();

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.minusMonths(-1);

        return new InvoiceScheduleBuilder()
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withDescription(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH )+ " Invoice")
                .build();
    }

}
