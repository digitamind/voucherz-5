package com.interswitch.valuevoucherz.api.controller.util;

import com.interswitch.valuevoucherz.api.controller.ValueVoucherController;
import com.interswitch.valuevoucherz.api.model.request.Voucher;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class VoucherResourceAssembler implements ResourceAssembler<Voucher, Resource<Voucher>> {

    @Override
    public Resource<Voucher> toResource(Voucher voucher) {
        return new Resource<>(voucher,
                linkTo(methodOn(ValueVoucherController.class).getVoucherByCode(voucher.getCode(), null)).withSelfRel(),
                linkTo(methodOn(ValueVoucherController.class).getVoucherByMerchantUser(null)).withRel("Value Vouchers"));
    }
}
