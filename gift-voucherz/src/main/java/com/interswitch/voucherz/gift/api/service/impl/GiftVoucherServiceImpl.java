package com.interswitch.voucherz.gift.api.service.impl;

import com.interswitch.voucherz.gift.api.dao.GiftVoucherDao;
import com.interswitch.voucherz.gift.api.exception.RequestException;
import com.interswitch.voucherz.gift.api.model.Page;
import com.interswitch.voucherz.gift.api.model.request.*;
import com.interswitch.voucherz.gift.api.model.response.Response;
import com.interswitch.voucherz.gift.api.service.DistributionService;
import com.interswitch.voucherz.gift.api.service.GiftVoucherService;
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
public class GiftVoucherServiceImpl<T> implements GiftVoucherService<T> {

    @Autowired
    private GiftVoucherDao<T> dao;

    @Autowired
    DistributionService distributionService;

    @Override
    public void createSingleVoucher(GiftVoucher voucher){
        if(dao.createSingleVoucher(voucher)) {
            distributionService.sendStandaloneVoucher(voucher);
        }
        else
            throw new RequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Create Operation Failed!");
    }

    
    @Override
    public void createBulk(BulkVouchers bulkVouchers){
        SQLServerDataTable vouchersTvp = null;
        CodeConfig codeConfig = bulkVouchers.getCodeConfig();
        Integer numOfVouchers = bulkVouchers.getQuantity();
        GiftVoucher model = bulkVouchers.getGiftVoucher();
        try {
            vouchersTvp = getTvpWithMetadata();
            addTableRows(model, vouchersTvp, codeConfig);
        } catch (SQLServerException e) {
            throw new RequestException(HttpStatus.INTERNAL_SERVER_ERROR, e.getClass().getName()+" : "+e.getMessage());
        }
        if(dao.createBulk(vouchersTvp))
            distributionService.sendStandaloneVoucher(model);
        throw new RequestException(HttpStatus.INTERNAL_SERVER_ERROR, "Create Operation Failed!");
    }

    private SQLServerDataTable getTvpWithMetadata() throws SQLServerException {
        SQLServerDataTable vouchers;
        vouchers = new SQLServerDataTable();
        vouchers.setTvpName("[dbo].[voucher_table_param]");
        vouchers.addColumnMetadata("setCode", Types.NVARCHAR);
        vouchers.addColumnMetadata("amount", Types.BIGINT);
        vouchers.addColumnMetadata("dateCreated", Types.DATE);
        vouchers.addColumnMetadata("expiryDate", Types.DATE);
        vouchers.addColumnMetadata("isActive", Types.BIT);
        vouchers.addColumnMetadata("isCorporate", Types.BIT);
        vouchers.addColumnMetadata("category", Types.NVARCHAR);
        vouchers.addColumnMetadata("campaignId", Types.NVARCHAR);
        vouchers.addColumnMetadata("customerId", Types.NVARCHAR);
        vouchers.addColumnMetadata("merchantId", Types.NVARCHAR);
        vouchers.addColumnMetadata("productId", Types.NVARCHAR);
        vouchers.addColumnMetadata("userId", Types.NVARCHAR);
        vouchers.addColumnMetadata("merchantStoreId", Types.BIGINT);
        vouchers.addColumnMetadata("additionalInfo", Types.NVARCHAR);
        return vouchers;
    }

    private void addTableRows(GiftVoucher model, SQLServerDataTable vouchers, CodeConfig config) throws SQLServerException {
        for(int i = 0; i< config.getQuantity() ; i++){

            vouchers.addRow(VoucherCodeGenerator.generate(config),
                    model.getAmount(),
                    model.getDateCreated(),
                    model.getExpiryDate(),
                    model.getIsActive(),
                    model.getIsCorporate(),
                    model.getCategory(),
                    model.getCampaignId(),
                    model.getCustomerId(),
                    model.getMerchantId(),
                    model.getProductId(),
                    model.getUserId(),
                    model.getMerchantStoreId(),
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
            return new Response(HttpStatus.OK, "UnDelete Request Completed!", null);
        }
        throw  new RequestException(HttpStatus.BAD_REQUEST, "Request Failed!");
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
    public List<T> getVoucherByActiveStatus(T model) {
        return dao.getVoucherByActiveStatus(model);
    }

    @Override
    public List<T> getVoucherByExpiryDate(T model) {
        return dao.getVoucherByExpiryDate(model);
    }

    @Override
    public T validateVoucher(T model) {
        return dao.validateVoucher(model);
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
    public T redeem(T model) {
        return dao.redeem(model);
    }

    @Override
    public T addBalance(T model) {
        return dao.addBalance(model);
    }

    @Override
    public Page getAll(int pageNumber, int pageSize) {
        return dao.getAll(pageNumber,pageSize);
    }

    @Override
    public T getVoucherByCode(T request) {
        return dao.getVoucherByCode(request);
    }

}
