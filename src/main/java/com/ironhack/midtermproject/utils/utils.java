package com.ironhack.midtermproject.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public static String PasswordEncodeUtil (String originalPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = passwordEncoder.encode(originalPassword);
        System.out.println(passwordEncode);
        return passwordEncode;

    }

}
