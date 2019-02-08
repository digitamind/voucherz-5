package com.interswitch.voucherz.authservice.service;

import com.interswitch.voucherz.authservice.models.Merchant;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.models.Page;

import java.util.List;

public interface MerchantService {
    public MerchantUser createMerchant(MerchantUser merchantUser);
    public MerchantUser findMerchantByUsername(String username);
    public MerchantUser findMerchantByUsernameAndPassword(String username, String password);
    public void changeMerchantStatus(MerchantUser merchantUser, boolean isEnabled);
    public void changeMerchantUserPassword(String username, String password);
    public Page<Merchant> getAllMerchants(int pageSize, int pageNumber);
    public Page<MerchantUser> getAllMerchantUsers(int pageSize, int pageNumber);
    public List<Merchant> getAllMerchantTest(int pageNumber, int pageSize);
}
