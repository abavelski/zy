package com.zy.app.fee.main;

import com.zy.app.fee.model.Fee;

import java.time.LocalDate;

/**
 * alexei.bavelski@gmail.com
 * 25/07/15
 */
public interface FeeDescriptionService {

    String getFeeDescription(Fee fee, LocalDate nextChargeDate);

}
