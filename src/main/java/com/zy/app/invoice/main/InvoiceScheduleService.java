package com.zy.app.invoice.main;


import com.zy.app.invoice.model.InvoiceSchedule;

import java.time.LocalDateTime;

public interface InvoiceScheduleService {

    InvoiceSchedule getScheduleFor(LocalDateTime date);

}
