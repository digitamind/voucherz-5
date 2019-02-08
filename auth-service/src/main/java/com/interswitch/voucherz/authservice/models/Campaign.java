package com.interswitch.voucherz.authservice.models;

import java.util.Date;

public class Campaign extends BaseEntity {
    private String name;
    private String description;
    private int activate;
    private Date startDate;
    private Date expirationDate;
    private int voucherType;
    private long merchantId;
    private long merchantUserId;

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public long getMerchantUserId() {
        return merchantUserId;
    }

    public void setMerchantUserId(long merchantUserId) {
        this.merchantUserId = merchantUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public int isActive() {
//        return this.active;
//    }
//
//    public void setActivate(int isActive) {
//        this.active = isActive;
//    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(int voucherType) {
        this.voucherType = voucherType;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
//                ", active=" + active +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                ", voucherType=" + voucherType +
                ", merchantId=" + merchantId +
                ", merchantUserId=" + merchantUserId +
                '}';
    }
}
