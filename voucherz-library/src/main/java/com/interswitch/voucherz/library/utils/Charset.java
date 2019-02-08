package com.interswitch.voucherz.library.utils;


public final class Charset {
    public static final String ALPHABETIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHANUMERIC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";

    public static String getCharSet(CharsetType type){
        switch(type){
            case ALPHABETIC: return Charset.ALPHABETIC;
            case ALPHANUMERIC: return Charset.ALPHANUMERIC;
            case NUMBERS: return Charset.NUMBERS;
            default:
                return Charset.ALPHANUMERIC;
        }
    }
}