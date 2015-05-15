package com.zy.app.common.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aba
 * 14/03/15
 */
public class DbUtil {

    public static LocalDate fromDB(Date date) {
        return date!=null?date.toLocalDate():null;
    }

    public static LocalDateTime fromDB(Timestamp timestamp) {
        return timestamp!=null?timestamp.toLocalDateTime():null;
    }

    public static Timestamp toDB(LocalDateTime dateTime) {
        return dateTime !=null ? Timestamp.valueOf(dateTime):null;
    }

    public static Date toDB(LocalDate date) {
        return date !=null ? Date.valueOf(date):null;
    }

}
