package com.interswitch.voucherz.notificationservice.model;

import java.io.Serializable;

public class VoucherMail implements Serializable {
    private String emailAddress;
    private String voucherCode;
    private int voucherType;
    private String merchantName;
    private String voucherDescription;

    private String voucherTypeName;

    public void setVoucherTypeName(){
        switch (voucherType){
            case 1:
                voucherTypeName = "Gift";
                break;
            case 2:
                voucherTypeName = "Discount";
                break;
            case 3:
                voucherTypeName = "Value";
                break;
            default:
                voucherTypeName = "";
        }
    }

    public String getVoucherTypeName(){
        return voucherTypeName;
    }

    public String getEmailAddress() {
        return emailAddress;
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

    public int getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(int voucherType) {
        this.voucherType = voucherType;
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
                "You have been awarded a %s voucher:\n"+
                        "Code: %s\n" +
                "from:%s" +
                        "Description: %s"

                ,
                voucherTypeName, voucherCode, merchantName, voucherDescription
        );

        return mailMessage;
    }
}
