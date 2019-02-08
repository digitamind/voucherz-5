package com.interswitch.voucherz.authservice.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TokenExpiration {

    public static Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

}
