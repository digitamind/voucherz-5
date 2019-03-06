package com.interswitch.discountvoucherz.api.dao.impl;

import com.interswitch.discountvoucherz.api.dao.AbstractBaseDao;
import com.interswitch.discountvoucherz.api.dao.DiscountVoucherDao;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import com.interswitch.discountvoucherz.api.model.response.VoucherEntitiy;
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

@Slf4j
@Repository
public class DiscountVoucherDaoImpl<T> extends AbstractBaseDao<T> implements DiscountVoucherDao<T> {
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
            redeem,
            getVoucherByProduct,
            updateValue;


    @Autowired
    @Override
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        createSingle = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCreateVoucher").withReturnValue();
        createBulk = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCreateVouchers").withReturnValue();
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspUpdateVoucher").withReturnValue();
        updateValue = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspUpdateVoucherValue").withReturnValue();
        getVoucherByCode = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCode")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(DiscountVoucher.class));
        getVoucherByCampaign = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCampaign")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(DiscountVoucher.class));
        getVoucherByDateCreated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByDateCreated")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(DiscountVoucher.class));
        getVoucherByActiveStatus = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByActiveStatus")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(DiscountVoucher.class));
        getVoucherByExpiryDate = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByExpiryDate")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(DiscountVoucher.class));
        getVoucherByMerchantUser = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByMerchantUser")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(DiscountVoucher.class));
        delete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspDeleteVoucher").withReturnValue();
        validateVoucher = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspValidateVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(DiscountVoucher.class));
        getVoucherByCustomer = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCustomer")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(DiscountVoucher.class));
        getVoucherByProduct = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByProduct")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(DiscountVoucher.class));
        unDelete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspUnDeleteVoucher").withReturnValue();
        disable = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspDisableVoucher")
                .withReturnValue();
        enable = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspEnableVoucher")
                .withReturnValue();
        redeem = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspRedeemVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(DiscountVoucher.class));

    }

    @Override
    public Boolean createBulk(SQLServerDataTable voucherTvp){
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("vouchers", voucherTvp);
        return withReturnValue(in, createBulk);
    }

    public boolean createSingleVoucher(T model) throws DataAccessException {
        log.info("Create request: "+model.toString());
        SqlParameterSource params = new BeanPropertySqlParameterSource(model);
        return withReturnValue(params, createSingle);
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
    public Boolean updateVoucherValue(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withReturnValue(in, updateValue);
    }

    @Override
    public boolean enable(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withReturnValue(in, enable);
    }

    @Override
    public boolean disable(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withReturnValue(in, disable);
    }

    @Override
    public boolean unDelete(T model) {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withReturnValue(in, unDelete);
    }

    @Override
    public boolean delete(T model){
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withReturnValue(in, delete);
    }

}
