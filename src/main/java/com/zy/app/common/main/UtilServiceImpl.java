package com.zy.app.common.main;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UtilServiceImpl implements UtilService {
    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public String getRandomKey() {
        return UUID.randomUUID().toString();
    }
}
