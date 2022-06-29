package com.ironhack.midtermproject.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class utils {
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date getDateNow(){
        LocalDateTime now = LocalDateTime.now();
        Date today = java.sql.Timestamp.valueOf(now);
        return today;
    }
}
