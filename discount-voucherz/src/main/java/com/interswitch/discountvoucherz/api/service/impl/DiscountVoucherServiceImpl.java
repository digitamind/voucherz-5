package com.interswitch.discountvoucherz.api.service.impl;

import com.interswitch.discountvoucherz.api.dao.DiscountVoucherDao;
import com.interswitch.discountvoucherz.api.exception.RequestException;
import com.interswitch.discountvoucherz.api.model.Page;
import com.interswitch.discountvoucherz.api.model.Response;
import com.interswitch.discountvoucherz.api.model.request.BulkVouchers;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import com.interswitch.discountvoucherz.api.service.DiscountVoucherService;
import com.interswitch.voucherz.library.model.CodeConfig;
import com.interswitch.voucherz.library.utils.VoucherCodeGenerator;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;

@Slf4j
@Service
public class DiscountVoucherServiceImpl<T> implements DiscountVoucherService<T> {

    @Autowired
    private DiscountVoucherDao<T> dao;


    @Override
    public Response createSingleVoucher(T voucher){
        if(dao.createSingleVoucher(voucher))
            return new Response(HttpStatus.CREATED, "Created Successfully!", null);
        throw new RequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Create Operation Failed!");
    }

    
    @Override
    public Response createBulk(BulkVouchers bulkVouchers){
        SQLServerDataTable vouchersTvp = null;
        CodeConfig codeConfig = bulkVouchers.getCodeConfig();
        DiscountVoucher model = bulkVouchers.getDiscountVoucher();
        try {
            vouchersTvp = getTvpWithMetadata();
            addTableRows(model, vouchersTvp, codeConfig);
        } catch (SQLServerException e) {
            throw new RequestException(HttpStatus.INTERNAL_SERVER_ERROR, e.getClass().getName()+" : "+e.getMessage());
        }

        if(dao.createBulk(vouchersTvp))
            return new Response(HttpStatus.CREATED, "Created Successfully!", null);
        throw new RequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Create Operation Failed!");
    }

    private SQLServerDataTable getTvpWithMetadata() throws SQLServerException {
        SQLServerDataTable vouchers;
        vouchers = new SQLServerDataTable();
        vouchers.setTvpName("[dbo].[voucher_table_param]");
        vouchers.addColumnMetadata("code", Types.NVARCHAR);
        vouchers.addColumnMetadata("type", Types.SMALLINT);
        vouchers.addColumnMetadata("value", Types.DECIMAL);
        vouchers.addColumnMetadata("expiryDate", Types.DATE);
        vouchers.addColumnMetadata("campaignId", Types.NVARCHAR);
        vouchers.addColumnMetadata("customerId", Types.NVARCHAR);
        vouchers.addColumnMetadata("merchantId", Types.NVARCHAR);
        vouchers.addColumnMetadata("productId", Types.NVARCHAR);
        vouchers.addColumnMetadata("userId", Types.NVARCHAR);
        vouchers.addColumnMetadata("isActive", Types.BIT);
        vouchers.addColumnMetadata("category", Types.NVARCHAR);
        vouchers.addColumnMetadata("additionalInfo", Types.NVARCHAR);
        return vouchers;
    }

    private void addTableRows(DiscountVoucher model, SQLServerDataTable vouchers, CodeConfig config) throws SQLServerException {
        for(int i = 0; i< config.getQuantity() ; i++){

            vouchers.addRow(VoucherCodeGenerator.generate(config),
                    model.getType(),
                    model.getValue(),
                    model.getExpiryDate(),
                    model.getCampaignId(),
                    model.getCustomerId(),
                    model.getMerchantId(),
                    model.getProductId(),
                    model.getUserId(),
                    model.getIsActive(),
                    model.getCategory(),
                    model.getAdditionalInfo());
        }
    }

    @Override
    public Response update(T model) {
        if(dao.update(model)){
            return new Response(HttpStatus.OK, "Update Successful!", null);
        }
        throw  new RequestException(HttpStatus.BAD_REQUEST, "Update Request Failed!");
    }

    @Override
    public Response delete(T model) {
        if(dao.delete(model)){
            return new Response(HttpStatus.OK, "Deleted Successfully!", null);
        }
        throw  new RequestException(HttpStatus.BAD_REQUEST, "Voucher Cannot Be Deleted!");
    }

    @Override
    public T redeem(T model) {
        return dao.redeem(model);
    }

    @Override
    public T addBalance(T model) {
        return dao.addBalance(model);
    }

    @Override
    public Response enable(T model) {
        if(dao.enable(model)){
            return new Response(HttpStatus.OK, "Enabled Successfully!", null);
        }
        throw  new RequestException(HttpStatus.BAD_REQUEST, "Voucher Not Enabled!");
    }

    @Override
    public Response disable(T model) {
        if(dao.disable(model)){
            return new Response(HttpStatus.OK, "Disabled Successfully!", null);
        }
        throw  new RequestException(HttpStatus.BAD_REQUEST, "Voucher Cannot Be Disabled!");
    }

    @Override
    public Response unDelete(T model) {
        if(dao.unDelete(model)){
            return new Response(HttpStatus.OK, "Deleted Successfully!", null);
        }
        throw  new RequestException(HttpStatus.BAD_REQUEST, "Delete Request Failed!");
    }


    @Override
    public Page findAll(int pageNumber, int pageSize) {
        return dao.getAll(pageNumber,pageSize);
    }

    @Override
    public List<T> getVoucherByMerchantUser(T model) {
        return dao.getVoucherByMerchantUser(model);
    }

    @Override
    public List<T> getVoucherByCampaign(T model) {
        return dao.getVoucherByCampaign(model);
    }

    @Override
    public List<T> getVoucherByDateCreated(T model) {
        return dao.getVoucherByDateCreated(model);
    }

    @Override
    public T getVoucherByCode(T request) {
        return dao.getVoucherByCode(request);
    }

    @Override
    public List<T> getVoucherByActiveStatus(T model) {
        return dao.getVoucherByActiveStatus(model);
    }

    @Override
    public List<T> getVoucherByExpiryDate(T model) {
        return dao.getVoucherByExpiryDate(model);
    }

    @Override
    public List<T> getVoucherByCustomer(T model) {
        return dao.getVoucherByCustomer(model);
    }

    @Override
    public List<T> getVoucherByProduct(T model) {
        return dao.getVoucherByProduct(model);
    }

    @Override
    public T validateVoucher(T model) {
        return dao.validateVoucher(model);
    }

}
