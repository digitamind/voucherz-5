package com.interswitch.valuevoucherz.api.controller;

import com.interswitch.valuevoucherz.api.controller.util.VoucherResourceAssembler;
import com.interswitch.valuevoucherz.api.model.request.*;
import com.interswitch.valuevoucherz.api.model.response.VoucherResponse;
import com.interswitch.valuevoucherz.api.service.VoucherService;
import com.interswitch.valuevoucherz.api.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/value-voucher")
@Slf4j
public class
ValueVoucherController {
    @Autowired
    private VoucherService<Voucher> service;

    @Autowired
    private VoucherResourceAssembler assembler;

    private Voucher model;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Resource<VoucherResponse> createVoucher(@RequestBody BulkVouchers bulkVouchers, HttpServletRequest request){
        log.info(bulkVouchers.toString());
        model = bulkVouchers.getVoucher();
        UserUtil.setCredentials(request, model);
        bulkVouchers.setVoucher(model);
        return new Resource<>(service.createBulk(bulkVouchers));
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public Resource<Voucher> createVoucher(@PathVariable("code") String code, @RequestBody Voucher model, HttpServletRequest request){
        UserUtil.setCredentials(request, model);
        model.setCode(code);
        return assembler.toResource(service.createSingleVoucher(model));
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public Resource<Voucher> getVoucherByCode(@PathVariable("code") String code, HttpServletRequest request){
        log.info("setCode: "+code);
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setCode(code);
        return assembler.toResource(service.getVoucherByCode(model));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Resources<Resource<Voucher>> getVoucherByMerchantUser(HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        List<Resource<Voucher>> vouchers = service.getVoucherByMerchantUser(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(ValueVoucherController.class).getVoucherByMerchantUser(request)).withSelfRel());
    }

    @RequestMapping(value = "/{code}/update", method = RequestMethod.PUT)
    public Resource<Voucher> updateVoucher(@RequestBody UpdateVoucher voucherUpdate, HttpServletRequest request){
        log.info("Update Voucher "+voucherUpdate.toString());
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setIsActive(voucherUpdate.getIsActive());
        model.setAdditionalInfo(voucherUpdate.getAdditionalInfo());
        model.setExpiryDate(voucherUpdate.getExpiryDate());
        return assembler.toResource(service.update(model));
    }

    @RequestMapping(value = "/{code}/enable", method = RequestMethod.POST)
    public Resource<Voucher> enable(@PathVariable("code") EnableVoucher enableVoucher, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setCode(enableVoucher.getCode());
        return assembler.toResource(service.enable(model));
    }

    @RequestMapping(value = "/{code}/disable", method = RequestMethod.POST)
    public Resource<Voucher> disable(@PathVariable("code") DisableVoucher disableVoucher, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setCode(disableVoucher.getCode());
        return assembler.toResource(service.disable(model));
    }

    @RequestMapping(value = "/{code}/validate", method = RequestMethod.POST)
    public Resource<Voucher> validateVoucher(@PathVariable("code") String code, @RequestBody Voucher model, HttpServletRequest request){
        UserUtil.setCredentials(request, model);
        model.setCode(code);
        return assembler.toResource(service.validateVoucher(model));
    }

    @RequestMapping(value = "/{code}/redeem", method = RequestMethod.POST)
    public Resource<Voucher> redeem(@PathVariable("code") String code, @RequestBody RedeemVoucher redeemVoucher, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model );
        model.setCode(code);
        model.setAmount(redeemVoucher.getOrderAmount());
        return assembler.toResource(service.redeem(model));
    }

    @RequestMapping(value = "/{code}/balance", method = RequestMethod.POST)
    public Resource<Voucher> addBalance(@PathVariable("code") String code, @RequestBody AddBalance addBalance, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request,model);
        model.setCode(code);
        model.setAmount(addBalance.getAmount());
        return assembler.toResource(service.addBalance(model));
    }
    @RequestMapping(value = "/{code}/delete", method = RequestMethod.DELETE)
    public Resource<Boolean> delete(@PathVariable("code") String code, @RequestBody Voucher model, HttpServletRequest request){
        UserUtil.setCredentials(request, model);
        model.setCode(code);
        return new Resource<>(service.delete(model));
    }

    @RequestMapping(value = "/{code}/undelete", method = RequestMethod.DELETE)
    public Resource<Voucher> unDelete(@PathVariable("code") String code, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setCode(code);
        return assembler.toResource(service.unDelete(model));
    }

    @RequestMapping(value = "/{campaignId}/campaign", method = RequestMethod.GET)
    public Resources<Resource<Voucher>> getVoucherByCampaign(@PathVariable String campaignId, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setCampaignId(campaignId);
        List<Resource<Voucher>> vouchers = service.getVoucherByCampaign(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(ValueVoucherController.class)
                        .getVoucherByCode(model.getCode(), null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{date}/date_created", method = RequestMethod.GET)
    public Resources<Resource<Voucher>> getVoucherByDateCreated(@PathVariable Timestamp date, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setDateCreated(date);
        List<Resource<Voucher>> vouchers = service.getVoucherByDateCreated(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(ValueVoucherController.class)
                        .getVoucherByDateCreated(date, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{status}/status", method = RequestMethod.GET)
    public Resources<Resource<Voucher>> getVoucherByActiveStatus(@PathVariable Boolean status, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setIsActive(status);
        List<Resource<Voucher>> vouchers = service.getVoucherByActiveStatus(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(ValueVoucherController.class)
                        .getVoucherByActiveStatus(status, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{expiryDate}/expiry_date", method = RequestMethod.GET)
    public Resources<Resource<Voucher>> getVoucherByExpiryDate(@PathVariable Timestamp expiryDate, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setExpiryDate(expiryDate);
        List<Resource<Voucher>> vouchers = service.getVoucherByExpiryDate(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(ValueVoucherController.class)
                        .getVoucherByExpiryDate(expiryDate, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{customerId}/customer", method = RequestMethod.GET)
    public Resources<Resource<Voucher>> getVoucherByCustomer(@PathVariable String customerId, HttpServletRequest request){
        model = new Voucher();
        UserUtil.setCredentials(request, model);
        model.setCustomerId(customerId);
        List<Resource<Voucher>> vouchers = service.getVoucherByCustomer(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(ValueVoucherController.class)
                        .getVoucherByCustomer(customerId, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public Resource<String> addBalance(@RequestBody Payment payment, HttpServletRequest request){
        payment.setMerchantId(String.valueOf(request.getSession().getAttribute("merchantId")));
        model.setUserId((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new Resource("Payment Not Available");
    }
}
