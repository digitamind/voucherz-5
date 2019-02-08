package com.interswitch.voucherz.gift.api.controller.util;

import com.interswitch.voucherz.gift.api.controller.GiftVoucherController;
import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class VoucherResourceAssembler implements ResourceAssembler<GiftVoucher, Resource<GiftVoucher>> {

    @Override
    public Resource<GiftVoucher> toResource(GiftVoucher voucher) {
        return new Resource<>(voucher,
                linkTo(methodOn(GiftVoucherController.class).getVoucherByCode(voucher.getCode(), null)).withSelfRel(),
                linkTo(methodOn(GiftVoucherController.class).getVoucherByMerchantUser(null)).withRel("GiftVouchers"));
    }
}
