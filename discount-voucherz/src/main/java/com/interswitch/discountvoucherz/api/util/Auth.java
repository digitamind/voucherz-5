package com.interswitch.discountvoucherz.api.util;

import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public class Auth {
    public static void setCredentials(HttpServletRequest request, DiscountVoucher model) {
        model.setMerchantId(String.valueOf(request.getSession().getAttribute("merchantId")));
        model.setUserId(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }
}