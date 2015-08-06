package com.zy.app.invoice.main;


import com.zy.app.invoice.model.InvoiceSchedule;

import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceScheduleService {

    InvoiceSchedule getScheduleFor(LocalDateTime date);
    List<InvoiceSchedule> getLastSchedulesFor(LocalDateTime date, int n);

}
