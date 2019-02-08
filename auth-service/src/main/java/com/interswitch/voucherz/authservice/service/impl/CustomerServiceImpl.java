package com.interswitch.voucherz.authservice.service.impl;

import com.interswitch.voucherz.authservice.dao.CustomerDao;
import com.interswitch.voucherz.authservice.models.Customer;
import com.interswitch.voucherz.authservice.service.CustomerService;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDao.create(customer);
    }

    @Override
    public Customer getCustomerById(String guid, long merchantId) {
        return customerDao.getCustomerById(guid, merchantId);
    }

    @Override
    public List<Customer> getAllCustomers(long merchantId) {
        return customerDao.getAllCustomers(merchantId);
    }

    @Override
    public void deleteCustomer(String guid, long merchantId) {
        customerDao.deleteCustomer(guid, merchantId);
    }

    @Override
    @Async
    public void insertCustomers(List<Customer> customers, long merchantId) throws SQLServerException {

        SQLServerDataTable customersTable = new SQLServerDataTable();
        customersTable.setTvpName("[dbo].[customer_table_param]");
        customersTable.addColumnMetadata("firstName", Types.NVARCHAR);
        customersTable.addColumnMetadata("lastName", Types.NVARCHAR);
        customersTable.addColumnMetadata("email", Types.NVARCHAR);
        customersTable.addColumnMetadata("address", Types.NVARCHAR);
        customersTable.addColumnMetadata("city", Types.NVARCHAR);
        customersTable.addColumnMetadata("country", Types.NVARCHAR);
        customersTable.addColumnMetadata("amountOfOrders", Types.BIGINT);
        customersTable.addColumnMetadata("numberOfOrders", Types.BIGINT);
        customersTable.addColumnMetadata("lastOrderAmount", Types.BIGINT);
        customersTable.addColumnMetadata("lastOrderDate", Types.DATE);
        customersTable.addColumnMetadata("dateJoined", Types.DATE);
        customersTable.addColumnMetadata("kpi", Types.INTEGER);
        customersTable.addColumnMetadata("merchantId", Types.BIGINT);

        for (Customer customer : customers){
            customersTable.addRow(customer.getFirstName(), customer.getLastName(),
                    customer.getEmail(), customer.getAddress(), customer.getCity(),
                    customer.getCountry(), customer.getAmountOfOrders(), customer.getNumberOfOrders(),
                    customer.getLastOrderAmount(), customer.getLastOrderDate(), customer.getDateJoined(),
                    customer.getKpi(), merchantId);
        }

        customerDao.insertCustomers(customersTable);
    }
}
