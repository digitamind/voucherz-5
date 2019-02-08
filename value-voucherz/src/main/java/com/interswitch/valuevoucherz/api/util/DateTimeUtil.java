package com.interswitch.valuevoucherz.api.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static LocalDateTime format(String date){
        DateTimeFormatter dateFormat = DateTimeFormatter.RFC_1123_DATE_TIME;
        return LocalDateTime.parse(date, dateFormat);
    }
}
