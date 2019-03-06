package com.interswitch.discountvoucherz.api.dao;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;

import java.util.List;

public interface DiscountVoucherDao<T> extends BaseDao<T>{
    Boolean createBulk(SQLServerDataTable model);

    boolean createSingleVoucher(T model);

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

    Boolean updateVoucherValue(T model);

    boolean enable(T model);

    boolean disable(T model);

    boolean unDelete(T model);
}
