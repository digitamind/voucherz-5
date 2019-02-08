package com.interswitch.voucherz.authservice.controller.model;

import javax.validation.constraints.*;
import java.util.Date;

public class CampaignRequest {

    private String name;
    private String description;
    @Min(value = 0, message = "activate should not be less than 0")
    @Max(value = 1, message = "activate should not be greater than 1")
    private int activate;

    @Min(value = 0, message = "activate should not be less than 0")
    @Max(value = 3, message = "activate should not be greater than 1")
    private int voucherType;

    @FutureOrPresent(message = "Date should be in the future or present")
    private Date startDate;

    @Future(message = "Date should be in the future")
    private Date expirationDate;

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

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public int getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(int voucherType) {
        this.voucherType = voucherType;
    }

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

    @Override
    public String toString() {
        return "CampaignRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", activate=" + activate +
                ", voucherType=" + voucherType +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
