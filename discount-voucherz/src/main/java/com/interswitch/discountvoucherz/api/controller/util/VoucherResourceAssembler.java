package com.interswitch.discountvoucherz.api.controller.util;


import com.interswitch.discountvoucherz.api.controller.DiscountVoucherController;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class VoucherResourceAssembler implements ResourceAssembler<DiscountVoucher, Resource<DiscountVoucher>> {

    @Override
    public Resource<DiscountVoucher> toResource(DiscountVoucher voucher) {
        return new Resource<>(voucher,
                linkTo(methodOn(DiscountVoucherController.class).getVoucherByCode(voucher.getCode(), null)).withSelfRel(),
                linkTo(methodOn(DiscountVoucherController.class).getVoucherByMerchantUser(null)).withRel("All"));
    }
}
