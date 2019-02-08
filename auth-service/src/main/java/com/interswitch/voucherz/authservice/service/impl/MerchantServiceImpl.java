package com.interswitch.voucherz.authservice.service.impl;

import com.interswitch.voucherz.authservice.Exception.CustomException;
import com.interswitch.voucherz.authservice.controller.MerchantController;
import com.interswitch.voucherz.authservice.dao.MerchantDao;
import com.interswitch.voucherz.authservice.models.DUserDetail;
import com.interswitch.voucherz.authservice.models.Merchant;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.models.Page;
import com.interswitch.voucherz.authservice.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService, UserDetailsService {

    @Autowired
    MerchantDao merchantDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(MerchantService.class);

    @Override
    public MerchantUser createMerchant(MerchantUser merchantUser) {

        //logger.info(merchantUser.toString());
        merchantUser.setPassword(passwordEncoder.encode(merchantUser.getPassword()));

        try{
            return merchantDao.create(merchantUser);
        }
        catch(DataAccessException e){
            logger.error(e.getMessage());
            throw new CustomException("Error Creating merchant", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @Override
    public MerchantUser findMerchantByUsername(String username) {
        MerchantUser merchantUser =  merchantDao.findByEmail(username);
        return merchantUser;
    }

    @Override
    public MerchantUser findMerchantByUsernameAndPassword(String username, String password) {
        return merchantDao.findByEmailAndPassword(username, passwordEncoder.encode(password));
    }

    @Override
    public void changeMerchantStatus(MerchantUser merchantUser, boolean isEnabled) {
        int status = true ? 1 : 0;
        merchantDao.changeUserStatus(merchantUser.getEmail(), status);
    }

    @Override
    public void changeMerchantUserPassword(String username, String password) {
        merchantDao.changePassword(username, passwordEncoder.encode(password));
    }

    @Override
    public Page<Merchant> getAllMerchants(int pageNumber, int pageSize) {
        return merchantDao.getAllMerchants(pageNumber, pageSize);
    }

    @Override
    public Page<MerchantUser> getAllMerchantUsers(int pageNumber, int pageSize) {
        return merchantDao.getAllMerchantUser(pageNumber, pageSize);
    }

    @Override
    public List<Merchant> getAllMerchantTest(int pageNumber, int pageSize) {
        return (List)merchantDao.getAllMerchantTest(pageNumber, pageSize);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MerchantUser user = findMerchantByUsername(s);
        if (user == null || user.getRole() == null) {
            throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }

        String [] authorities = new String[1];
        authorities[0] = "ROLE_" + user.getRole();

        boolean isEnabled = user.isEnabled() == 1 ? true : false;

        DUserDetail dUserDetail = new DUserDetail(user.getEmail(),user.getPassword(), user.getActive(),
                user.isLocked(), user.isExpired(),isEnabled, authorities);

        return dUserDetail;
    }
}
