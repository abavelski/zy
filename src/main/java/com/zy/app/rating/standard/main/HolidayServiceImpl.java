package com.zy.app.rating.standard.main;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * alexei.bavelski@gmail.com
 * 12/09/15
 */

@Component
public class HolidayServiceImpl implements HolidayService {
    @Override
    public boolean isHoliday(LocalDateTime localDateTime) {
        return false;
    }
}
