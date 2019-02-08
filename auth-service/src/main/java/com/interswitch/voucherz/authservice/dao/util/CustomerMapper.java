package com.interswitch.voucherz.authservice.dao.util;

import com.interswitch.voucherz.authservice.models.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerMapper implements RowMapper<Customer> {

    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setFirstName(rs.getString("firstName"));
        customer.setLastName(rs.getString("lastName"));
        customer.setPhoneNo(rs.getString("phoneNo"));
        customer.setAddress(rs.getString("address"));
        customer.setCity(rs.getString("city"));
        customer.setCountry(rs.getString("country"));
        customer.setAmountOfOrders(rs.getLong("amountOfOrders"));
        customer.setNumberOfOrders(rs.getLong("numberOfOrders"));
        customer.setLastOrderAmount(rs.getLong("lastOrderAmount"));
        customer.setDateJoined(rs.getDate("dateJoined"));
        customer.setLastOrderDate(rs.getDate("lastOrderDate"));
        customer.setMerchantId(rs.getLong("merchantId"));
        customer.setKpi(rs.getInt("kpi"));
        customer.setRowguid(rs.getString("rowguid"));

        return customer;
    }



}
