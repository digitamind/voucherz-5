package com.interswitch.voucherz.gift.api.model.distribution;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
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
