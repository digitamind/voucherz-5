package com.interswitch.voucherz.authservice.dao;

import com.interswitch.voucherz.authservice.models.Customer;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;


import java.util.List;

public interface CustomerDao extends BaseDao<Customer>{
    Customer getCustomerById(String guid, long merchantId);
    List<Customer> getAllCustomers(long merchantId);
    void deleteCustomer(String guid, long merchantId);
    void insertCustomers(SQLServerDataTable customersTable);
}
