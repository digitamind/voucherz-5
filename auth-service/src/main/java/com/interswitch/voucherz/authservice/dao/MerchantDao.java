package com.interswitch.voucherz.authservice.dao;

import com.interswitch.voucherz.authservice.models.Merchant;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.models.Page;


public interface MerchantDao extends BaseDao<MerchantUser>{
    MerchantUser findByEmail(String email);
    MerchantUser findByEmailAndPassword(String email, String password);
    void changeUserStatus(String username, int status);
    void changePassword(String username, String password);
    Page<MerchantUser> getAllMerchantUser(int pageNumber, int pageSize);
    Page<Merchant> getAllMerchants(int pageNumber, int pageSize);
    Page<Merchant> getAllMerchantTest(int pageNumber, int pageSize);
}
