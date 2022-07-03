package com.ironhack.midtermproject.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
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

    public static String passwordEncodeUtil(String originalPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = passwordEncoder.encode(originalPassword);
        System.out.println(passwordEncode);
        return passwordEncode;

    }

    public static String encoderBase64(String user, String password){
        String originalInput = user +":"+password;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        return "Basic "+encodedString;
    }

}
