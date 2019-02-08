package com.interswitch.valuevoucherz.api.dao;
import com.interswitch.valuevoucherz.api.model.response.VoucherResponse;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;

import java.util.List;

public interface VoucherDao<T> extends BaseDao<T>{
    VoucherResponse createBulk(SQLServerDataTable model);

    T createStandaloneVoucher(T model);

    T getVoucherByCode(T model);

    List<T> getVoucherByMerchantUser(T model);

    List<T> getVoucherByCustomer(T model);

    List<T> getVoucherByCampaign(T model);

    List<T> getVoucherByDateCreated(T model);

    List<T> getVoucherByActiveStatus(T model);

    List<T> getVoucherByExpiryDate(T model);

    T validateVoucher(T model);

    List<T> getVoucherByProduct(T model);

    T redeem(T model);

    T addBalance(T model);

    T enable(T model);

    T disable(T model);

    T unDelete(T model);
}
