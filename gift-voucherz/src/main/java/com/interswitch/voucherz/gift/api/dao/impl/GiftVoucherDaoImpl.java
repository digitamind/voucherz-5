package com.interswitch.voucherz.gift.api.dao.impl;

import com.interswitch.voucherz.gift.api.dao.AbstractBaseDao;
import com.interswitch.voucherz.gift.api.dao.GiftVoucherDao;
import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
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
public class GiftVoucherDaoImpl<T> extends AbstractBaseDao<T> implements GiftVoucherDao<T>{
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
        createSingle = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCreateGiftVoucher").withReturnValue();
        createBulk = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCreateGiftVouchers").withReturnValue();
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspAddVoucherBalance").withReturnValue();
        getVoucherByCode = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCode")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(GiftVoucher.class));
        getVoucherByCampaign = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCampaign")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(GiftVoucher.class));
        getVoucherByDateCreated = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByDateCreated")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(GiftVoucher.class));
        getVoucherByActiveStatus = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByActiveStatus")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(GiftVoucher.class));
        getVoucherByExpiryDate = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByExpiryDate")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(GiftVoucher.class));
        getVoucherByMerchantUser = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByMerchantUser")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(GiftVoucher.class));
        delete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspDeleteVoucher").withReturnValue();
        validateVoucher = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspValidateVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(GiftVoucher.class));
        getVoucherByCustomer = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByCustomer")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(GiftVoucher.class));
        getVoucherByProduct = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspGetVoucherByProduct")
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper(GiftVoucher.class));
        unDelete = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspUnDeleteVoucher").withReturnValue();
        disable = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspDisableVoucher").withReturnValue();
        enable = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspEnableVoucher").withReturnValue();
        addBalance = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspAddVoucherBalance")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(GiftVoucher.class));
        redeem = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspRedeemGiftVoucher")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(GiftVoucher.class));

    }

    @Override
    public boolean createBulk(SQLServerDataTable voucherTvp){
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("vouchers", voucherTvp);
        return withReturnValue(in, createBulk);
    }

    public boolean createSingleVoucher(GiftVoucher model) throws DataAccessException {
        SqlParameterSource in = new BeanPropertySqlParameterSource(model);
        return withReturnValue(in, createSingle);
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
