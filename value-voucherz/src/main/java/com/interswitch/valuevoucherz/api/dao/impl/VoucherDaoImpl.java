package com.interswitch.valuevoucherz.api.dao.impl;

import com.interswitch.valuevoucherz.api.dao.AbstractBaseDao;
import com.interswitch.valuevoucherz.api.dao.VoucherDao;
import com.interswitch.valuevoucherz.api.model.request.Voucher;
import com.interswitch.valuevoucherz.api.model.response.VoucherResponse;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class VoucherDaoImpl<T> extends AbstractBaseDao<T> implements VoucherDao<T> {
    protected SimpleJdbcCall createBulk,
            getVoucherByCode,
            getVoucherByMerchantUser,
            getVoucherByCampaign,
            getVoucherByDateCreated,
            getVoucherByActiveStatus,
            getVoucherByExpiryDate,
            validateVoucher,
            getVoucherByCustomer,
            unDelete,
            disable,
            enable,
            addBalance,
            redeem,
            getVoucherByProduct;


    @Autowired
    @Override
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        createSingle = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCreateVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper(Voucher.class));
        createBulk = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCreateVouchers").withReturnValue();
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspUpdateBalance").withReturnValue();
        getVoucherByCode = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCode")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        getVoucherByCampaign = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCampaign")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        getVoucherByDateCreated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByDateCreated")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        getVoucherByActiveStatus = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByActiveStatus")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        getVoucherByExpiryDate = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByExpiryDate")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        getVoucherByMerchantUser = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByMerchantUser")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(Voucher.class));
        delete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspDeleteVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        validateVoucher = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspValidateVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        getVoucherByCustomer = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCustomer")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(Voucher.class));
        getVoucherByProduct = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByProduct")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(Voucher.class));
        unDelete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspUnDeleteVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        disable = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspDisableVoucher")
                .withReturnValue();
        enable = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspEnableVoucher")
                .withReturnValue();
        addBalance = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspAddVoucherBalance")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));
        redeem = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspRedeemVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(Voucher.class));

    }

    @Override
    public VoucherResponse createBulk(SQLServerDataTable voucherTvp){
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("vouchers", voucherTvp);
        Map<String, Object> m = createBulk.execute(source);
        Integer statusCode = (Integer) m.get("RETURN_VALUE");
        Integer total = (Integer) m.get("#update-count-1");
        String message = "ERROR";
        if(statusCode == 0)
            message = "CREATED";
        return VoucherResponse.builder().status(message).total(total).build();
    }

    public T createStandaloneVoucher(T model) throws DataAccessException {
        SqlParameterSource params = new BeanPropertySqlParameterSource(model);
        return withSingleResultSet(params, createSingle);
    }

    @Override
    public T getVoucherByCode(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withSingleResultSet(in, getVoucherByCode);
    }

    @Override
    public List<T> getVoucherByMerchantUser(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withMultipleResultSet(in, getVoucherByMerchantUser);
    }

    @Override
    public List<T> getVoucherByCustomer(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withMultipleResultSet(in, getVoucherByCustomer);
    }

    @Override
    public List<T> getVoucherByCampaign(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withMultipleResultSet(in, getVoucherByCampaign);
    }


    @Override
    public List<T> getVoucherByDateCreated(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withMultipleResultSet(in, getVoucherByDateCreated);
    }

    @Override
    public List<T> getVoucherByActiveStatus(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withMultipleResultSet(in, getVoucherByActiveStatus);
    }

    @Override
    public List<T> getVoucherByExpiryDate(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withMultipleResultSet(in, getVoucherByExpiryDate);
    }

    @Override
    public T validateVoucher(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withSingleResultSet(in, validateVoucher);

    }

    @Override
    public List<T> getVoucherByProduct(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withMultipleResultSet(in, getVoucherByProduct);
    }

    @Override
    public T redeem(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withSingleResultSet(in, redeem);
    }

    @Override
    public T addBalance(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withSingleResultSet(in, addBalance);
    }

    @Override
    public T enable(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withSingleResultSet(in, enable);
    }

    @Override
    public T disable(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withSingleResultSet(in, disable);
    }

    @Override
    public T unDelete(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withSingleResultSet(in, unDelete);
    }

    @Override
    public boolean delete(T model){
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withReturnValue(in, delete);
    }

}
