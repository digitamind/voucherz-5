package com.interswitch.valuevoucherz.api.dao.impl;

import com.interswitch.valuevoucherz.api.dao.AbstractBaseDao;
import com.interswitch.valuevoucherz.api.dao.VoucherDao;
import com.interswitch.valuevoucherz.api.exception.RequestException;
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
        return getSingleResult(params, createSingle);
    }

    @Override
    public T getVoucherByCode(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getSingleResult(in, getVoucherByCode);
    }

    @Override
    public List<T> getVoucherByMerchantUser(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getMultipleResult(in, getVoucherByMerchantUser);
    }

    @Override
    public List<T> getVoucherByCustomer(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getMultipleResult(in, getVoucherByCustomer);
    }

    @Override
    public List<T> getVoucherByCampaign(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getMultipleResult(in, getVoucherByCampaign);
    }


    @Override
    public List<T> getVoucherByDateCreated(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getMultipleResult(in, getVoucherByDateCreated);
    }

    @Override
    public List<T> getVoucherByActiveStatus(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getMultipleResult(in, getVoucherByActiveStatus);
    }

    @Override
    public List<T> getVoucherByExpiryDate(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getMultipleResult(in, getVoucherByExpiryDate);
    }

    @Override
    public T validateVoucher(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getSingleResult(in, validateVoucher);

    }

    @Override
    public List<T> getVoucherByProduct(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getMultipleResult(in, getVoucherByProduct);
    }

    @Override
    public T redeem(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getSingleResult(in, redeem);
    }

    @Override
    public T addBalance(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getSingleResult(in, addBalance);
    }

    @Override
    public T enable(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getSingleResult(in, enable);
    }

    @Override
    public T disable(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getSingleResult(in, disable);
    }

    @Override
    public T unDelete(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getSingleResult(in, unDelete);
    }

    @Override
    public boolean delete(T model){
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return getBooleanResult(in, delete);
    }



    public List<T> getMultipleResult(SqlParameterSource source, SimpleJdbcCall jdbcCall) {
        Map<String, Object> m = jdbcCall.execute(source);
        List<T> list = (List<T>) m.get(MULTIPLE_RESULT);
        if (list == null || list.isEmpty()) {
            throw new RequestException("No Record Found");
        }
        return (List<T>)m.get(MULTIPLE_RESULT);

    }

    private Boolean getBooleanResult(SqlParameterSource in, SimpleJdbcCall jdbcCall) {
        Map<String, Object> m = jdbcCall.execute(in);
        Integer return_value = (Integer) m.get("RETURN_VALUE");
        if (return_value == 0)
            return true;
        return false;
    }

}
