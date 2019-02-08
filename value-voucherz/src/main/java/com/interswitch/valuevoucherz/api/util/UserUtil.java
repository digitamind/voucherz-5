package com.interswitch.valuevoucherz.api.util;

import com.interswitch.valuevoucherz.api.model.request.Voucher;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {
    public static void setCredentials(HttpServletRequest request, Voucher model) {
        model.setMerchantId(String.valueOf(request.getSession().getAttribute("merchantId")));
        model.setUserId((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}