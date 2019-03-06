package com.interswitch.voucherz.notificationservice.model;

import java.io.Serializable;

public class VoucherMail implements Serializable {
    private String emailAddress;
    private String voucherCode;
    private String voucherType;
    private String merchantName;
    private String voucherDescription;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getVoucherDescription() {
        return voucherDescription;
    }

    public void setVoucherDescription(String voucherDescription) {
        this.voucherDescription = voucherDescription;
    }


    @Override
    public String toString() {
        String mailMessage = String.format(
                "You have been awarded a %s Voucher:\n"+
                        "Code: %s\n" +
                        "Description: %s"

                ,
                voucherType, voucherCode, voucherDescription
        );

        return mailMessage;
    }
}
