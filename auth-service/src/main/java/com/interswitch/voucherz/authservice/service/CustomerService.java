package com.interswitch.voucherz.authservice.service;

import com.interswitch.voucherz.authservice.models.Customer;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.util.List;

public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public Customer getCustomerById(String guid, long merchantId);
    public List<Customer> getAllCustomers(long merchantId);
    public void deleteCustomer(String guid, long merchantId);
    public void insertCustomers(List<Customer> customers, long merchantId) throws SQLServerException;
}
