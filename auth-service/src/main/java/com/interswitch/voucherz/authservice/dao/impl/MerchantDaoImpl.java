package com.interswitch.voucherz.authservice.dao.impl;

import com.interswitch.voucherz.authservice.dao.AbstractBaseDao;
import com.interswitch.voucherz.authservice.dao.MerchantDao;
import com.interswitch.voucherz.authservice.dao.util.RowCountMapper;
import com.interswitch.voucherz.authservice.models.Customer;
import com.interswitch.voucherz.authservice.models.Merchant;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.models.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class MerchantDaoImpl extends AbstractBaseDao<MerchantUser> implements MerchantDao {

    protected SimpleJdbcCall getAllMerchantsTest;

    @Autowired
    @Override
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("uspInsertMerchantUser").withReturnValue();
        changeUserStatus = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspChangeMerchantUserStatus");
        findByEmail = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCheckMerchantUserByEmail")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(MerchantUser.class));
        findByEmailAndPassword = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCheckMerchantUserExist")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(MerchantUser.class));
        changeMerchantUserPassword = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspChangeMerchantUserPassword");
        getAllMerchants = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetAllMerchants")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Merchant.class));
        getGetAllMerchantsUser = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetAllMerchantUser")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(MerchantUser.class));
        getAllMerchantsTest = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetAllMerchantTest")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Merchant.class));

    }


    @Override
    public MerchantUser findByEmail(String email) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("Email", email);
        Map<String, Object> m = findByEmail.execute(in);
        List<MerchantUser> list = (List<MerchantUser>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public MerchantUser findByEmailAndPassword(String email, String password) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("username", email)
                .addValue("password", password);
        Map<String, Object> m = findByEmailAndPassword.execute(in);
        List<MerchantUser> list = (List<MerchantUser>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void changeUserStatus(String username, int status) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("enabled", status)
                .addValue("email", username);
        changeUserStatus.execute(in);
    }

    @Override
    public void changePassword(String username, String password) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("email", username)
                .addValue("password", password);
        changeMerchantUserPassword.execute(in);
    }

    @Override
    public Page<MerchantUser> getAllMerchantUser(int pageNumber, int pageSize) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pageNumber", pageNumber)
                .addValue("pageSize", pageSize);
        Map<String, Object> m = getGetAllMerchantsUser.execute(in);
        List<MerchantUser> content = (List<MerchantUser>) m.get(MULTIPLE_RESULT);

        Page<MerchantUser> page = new Page<>((long)content.size(), content);
        return page;
    }

    @Override
    public Page<Merchant> getAllMerchants(int pageNumber, int pageSize) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pageSize", pageSize)
                .addValue("pageNumber", pageNumber);
        Map<String, Object> m = getAllMerchants.execute(in);
        List<Merchant> content = (List<Merchant>) m.get(MULTIPLE_RESULT);

        Page<Merchant> page = new Page<>((long)content.size(), content);
        return page;
    }

    @Override
    public Page<Merchant> getAllMerchantTest(int pageNumber, int pageSize) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pageSize", pageSize)
                .addValue("pageNumber", pageNumber);
        Map<String, Object> m = getAllMerchantsTest.execute(in);
        List<Merchant> content = (List<Merchant>) m.get(MULTIPLE_RESULT);

        Page<Merchant> page = new Page<>((long)content.size(), content);

        return page;
    }
}
