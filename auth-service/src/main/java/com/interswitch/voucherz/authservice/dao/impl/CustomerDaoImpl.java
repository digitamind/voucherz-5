package com.interswitch.voucherz.authservice.dao.impl;

import com.interswitch.voucherz.authservice.dao.AbstractBaseDao;
import com.interswitch.voucherz.authservice.dao.CustomerDao;
import com.interswitch.voucherz.authservice.dao.MerchantDao;
import com.interswitch.voucherz.authservice.dao.util.RowCountMapper;
import com.interswitch.voucherz.authservice.models.Campaign;
import com.interswitch.voucherz.authservice.models.Customer;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDaoImpl extends AbstractBaseDao<Customer> implements CustomerDao {

    protected SimpleJdbcCall findByGuid, findAllCustomer,
            deleteCustomer, insertCustomers;

    @Autowired
    @Override
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("uspInsertCustomer").withReturnValue();
        insertCustomers = new SimpleJdbcCall(dataSource).withProcedureName("uspInsertCustomers").withReturnValue();
        findByGuid = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetCustomer")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Customer.class));
        findAllCustomer = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetAllCustomers")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Customer.class));
        deleteCustomer = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspDeleteCustomer").withReturnValue();
    }


    @Override
    public Customer getCustomerById(String guid, long merchantId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("rowguid", guid)
                .addValue("merchantId", merchantId);
        Map<String, Object> m = findByGuid.execute(in);
        List<Customer> list = (List<Customer>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Customer> getAllCustomers(long merchantId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("merchantId", merchantId);
        Map<String, Object> m = findAllCustomer.execute(in);
        List<Customer> content = (List<Customer>) m.get(MULTIPLE_RESULT);

        return content;
    }

    @Override
    public void deleteCustomer(String guid, long merchantId) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("rowguid", guid)
                .addValue("merchantId", merchantId);
        deleteCustomer.execute(in);
    }

    @Override
    public void insertCustomers(SQLServerDataTable customersTable) throws DataAccessException {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("customers", customersTable);
        insertCustomers.execute(in);

    }
}
