package com.interswitch.voucherz.authservice.models;

import org.springframework.hateoas.core.Relation;

import java.util.Date;

@Relation(value = "data", collectionRelation ="data")
public class Merchant extends BaseEntity {
    private String companyName;
    private Date dateCreated;
    private int companySize;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getCompanySize() {
        return companySize;
    }

    public void setCompanySize(int companySize) {
        this.companySize = companySize;
    }
}
