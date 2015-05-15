package com.zy.app.common.main;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface UtilService {

    LocalDateTime getCurrentDateTime();
    LocalDate getCurrentDate();
    String getRandomKey();
}
