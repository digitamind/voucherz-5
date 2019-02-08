package com.interswitch.voucherz.authservice.models;

import org.springframework.hateoas.core.Relation;

@Relation(value = "data", collectionRelation ="data")
public class MerchantUser extends BaseEntity {

    private int companySize;

    private String companyName;
    private String firstName;
    private String lastName;
    private String email;
    private Long merchantId;
    private String role;
    private int isCorporate;
    private String password;

    private Integer active=1;
    private boolean isLocked =false;
    private boolean isExpired=false;
    private int enabled;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getIsCorporate() {
        return isCorporate;
    }

    public void setIsCorporate(int isCorporate) {
        this.isCorporate = isCorporate;
    }

    public int getCompanySize() {
        return companySize;
    }

    public void setCompanySize(int companySize) {
        this.companySize = companySize;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public int isEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "MerchantUser{" +
                "companySize=" + companySize +
                ", companyName='" + companyName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", merchantId=" + merchantId +
                ", role='" + role + '\'' +
                ", isCorporate='" + isCorporate + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", isLocked=" + isLocked +
                ", isExpired=" + isExpired +
                ", enabled=" + enabled +
                '}';
    }
}
