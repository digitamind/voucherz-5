package com.interswitch.discountvoucherz.api.model.distribution;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class VoucherMail implements Serializable {
    private String emailAddress;
    private String voucherCode;
    private String voucherType;
    private String merchantName;
    private String voucherDescription;


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
