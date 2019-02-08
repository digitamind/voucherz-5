package com.interswitch.voucherz.authservice.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

public class LogHelper{
    public static void logRequest(HttpServletRequest request, Logger log) {
        log.info("===========================request begin================================================");
        log.info("URI         : {}", request.getRequestURL());
        log.info("Method      : {}", request.getMethod());
        log.info("Timestamp : {}", timestamp());
        log.info("==========================request end================================================");
    }

    public static void logResponse(Logger log, HttpStatus httpStatus, Object obj){
        log.info("==========================response begin================================================");
        log.info("Status Code         : {}", httpStatus);
        log.info("Object response         : {}", obj.toString());
        log.info("Timestamp : {}", timestamp());
        log.info("==========================response end================================================");
    }

    private static Timestamp timestamp(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
