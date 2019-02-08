package com.interswitch.voucherz.gift.api.util;

import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {
    public static void setCredentials(HttpServletRequest request, GiftVoucher model) {
        model.setMerchantId(String.valueOf(request.getSession().getAttribute("merchantId")));
        model.setUserId((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}