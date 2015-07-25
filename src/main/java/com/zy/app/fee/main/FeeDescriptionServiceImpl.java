package com.zy.app.fee.main;

import com.zy.app.common.main.UtilService;
import com.zy.app.fee.model.Fee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * alexei.bavelski@gmail.com
 * 25/07/15
 */
@Component
public class FeeDescriptionServiceImpl implements FeeDescriptionService {

    @Autowired
    UtilService utilService;

    @Override
    public String getFeeDescription(Fee fee, LocalDate nextChargeDate) {
        if (Fee.Type.ONCE.equals(fee.getType())) {
            return fee.getDescription();
        } else {
            return new StringBuilder(fee.getDescription())
                    .append("(")
                    .append(utilService.getCurrentDate().toString())
                    .append( " -- ")
                    .append(nextChargeDate.toString())
                    .append(")").toString();
        }

    }
}
