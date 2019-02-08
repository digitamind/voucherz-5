package com.interswitch.voucherz.authservice.controller.model;

import javax.validation.constraints.Email;
import java.util.Date;

public class CustomerRequest {

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String address;
    private String city;
    private String country;
    private long amountOfOrders;
    private long numberOfOrders;
    private long lastOrderAmount;
    private Date lastOrderDate;
    private Date dateJoined;
    private String phoneNumber;
    private int kpi;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getAmountOfOrders() {
        return amountOfOrders;
    }

    public void setAmountOfOrders(long amountOfOrders) {
        this.amountOfOrders = amountOfOrders;
    }

    public long getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(long numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public long getLastOrderAmount() {
        return lastOrderAmount;
    }

    public void setLastOrderAmount(long lastOrderAmount) {
        this.lastOrderAmount = lastOrderAmount;
    }

    public Date getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public int getKpi() {
        return kpi;
    }

    public void setKpi(int kpi) {
        this.kpi = kpi;
    }
}
