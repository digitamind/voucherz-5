package com.interswitch.voucherz.gift.api.controller;

import com.interswitch.voucherz.gift.api.controller.util.VoucherResourceAssembler;
import com.interswitch.voucherz.gift.api.model.request.*;
import com.interswitch.voucherz.gift.api.model.response.*;
import com.interswitch.voucherz.gift.api.service.AuditTrailService;
import com.interswitch.voucherz.gift.api.service.DistributionService;
import com.interswitch.voucherz.gift.api.service.GiftVoucherService;
import com.interswitch.voucherz.gift.api.util.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/gift-voucher")
@Slf4j
public class
GiftVoucherController{
    @Autowired
    private GiftVoucherService<GiftVoucher> service;

    @Autowired
    private VoucherResourceAssembler assembler;

    private GiftVoucher model;

    @Autowired
    private AuditTrailService auditTrailService;

    @Autowired
    private DistributionService distributionService;


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response createVoucher(@RequestBody BulkVouchers bulkVouchers, HttpServletRequest request){
        log.info(bulkVouchers.toString());
        model = bulkVouchers.getGiftVoucher();
        setCredentials(request, model);
        bulkVouchers.setGiftVoucher(model);
        service.createBulk(bulkVouchers);
        auditTrailService.publishAudit(model.getUserId(), EventType.VOUCHER_CREATED,
                "Bulk vouchers generated");
        return new Response(HttpStatus.CREATED, "Created Successfully!", null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public Response createVoucher(@PathVariable("code") String code, @RequestBody GiftVoucher model, HttpServletRequest request){
        setCredentials(request, model);
        model.setCode(code);
        service.createSingleVoucher(model);
        auditTrailService.publishAudit(model.getUserId(), EventType.VOUCHER_CREATED,
                "Single voucher generated");
        return new Response(HttpStatus.CREATED, "Created Successfully!", null);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public Resource<GiftVoucher> getVoucherByCode(@PathVariable("code") String code, HttpServletRequest request){
        log.info("setCode: "+code);
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setCode(code);
        return assembler.toResource(service.getVoucherByCode(model));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Resources<Resource<GiftVoucher>> getVoucherByMerchantUser(HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        List<Resource<GiftVoucher>> vouchers = service.getVoucherByMerchantUser(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(GiftVoucherController.class).getVoucherByMerchantUser(request)).withSelfRel());
    }

    @RequestMapping(value = "/{code}/update", method = RequestMethod.PUT)
    public ResponseEntity<Response> updateVoucher(@RequestBody UpdateVoucher voucherUpdate, HttpServletRequest request){
        log.info("Update Voucher "+voucherUpdate.toString());
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setIsActive(voucherUpdate.getIsActive());
        model.setAdditionalInfo(voucherUpdate.getAdditionalInfo());
        model.setExpiryDate(voucherUpdate.getExpiryDate());
        model.setAmount(voucherUpdate.getAmount());
        return new ResponseEntity<>(service.update(model), HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}/enable", method = RequestMethod.PUT)
    public ResponseEntity<Response> enable(@PathVariable("code") String code, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setCode(code);
        return new ResponseEntity<>(service.enable(model), HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}/disable", method = RequestMethod.PUT)
    public ResponseEntity<Response> disable(@PathVariable("code") String code, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setCode(code);
        return new ResponseEntity<>(service.disable(model), HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}/validate", method = RequestMethod.POST)
    public Resource<GiftVoucher> validateVoucher(@PathVariable("code") String code, @RequestBody GiftVoucher model, HttpServletRequest request){
        setCredentials(request, model);
        model.setCode(code);
        return assembler.toResource(service.validateVoucher(model));
    }

    @RequestMapping(value = "/{code}/redeem", method = RequestMethod.POST)
    public Resource<GiftVoucher> redeem(@PathVariable("code") String code, @RequestBody RedeemVoucher redeemVoucher, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model );
        model.setCode(code);
        model.setAmount(redeemVoucher.getOrderAmount());
        return assembler.toResource(service.redeem(model));
    }

    @RequestMapping(value = "/{code}/balance", method = RequestMethod.POST)
    public Resource<GiftVoucher> addBalance(@PathVariable("code") String code, @RequestBody AddBalance addBalance, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request,model);
        model.setCode(code);
        model.setAmount(addBalance.getAmount());
        return assembler.toResource(service.addBalance(model));
    }
    @RequestMapping(value = "/{code}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@PathVariable("code") String code, HttpServletRequest request){
        setCredentials(request, model);
        model.setCode(code);
        return new ResponseEntity<>(service.delete(model), HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}/undelete", method = RequestMethod.PUT)
    public ResponseEntity<Response> unDelete(@PathVariable("code") String code, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setCode(code);
        return new ResponseEntity<>(service.unDelete(model), HttpStatus.OK);
    }

    @RequestMapping(value = "/{campaignId}/campaign", method = RequestMethod.GET)
    public Resources<Resource<GiftVoucher>> getVoucherByCampaign(@PathVariable("campaignId") String campaignId, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setCampaignId(campaignId);
        List<Resource<GiftVoucher>> vouchers = service.getVoucherByCampaign(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(GiftVoucherController.class)
                        .getVoucherByCode(model.getCode(), null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{date}/date_created", method = RequestMethod.GET)
    public Resources<Resource<GiftVoucher>> getVoucherByDateCreated(@PathVariable Timestamp date, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setDateCreated(date);
        List<Resource<GiftVoucher>> vouchers = service.getVoucherByDateCreated(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(GiftVoucherController.class)
                        .getVoucherByDateCreated(date, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{status}/status", method = RequestMethod.GET)
    public Resources<Resource<GiftVoucher>> getVoucherByActiveStatus(@PathVariable Boolean status, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setIsActive(status);
        List<Resource<GiftVoucher>> vouchers = service.getVoucherByActiveStatus(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(GiftVoucherController.class)
                        .getVoucherByActiveStatus(status, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{expiryDate}/expiry_date", method = RequestMethod.GET)
    public Resources<Resource<GiftVoucher>> getVoucherByExpiryDate(@PathVariable Timestamp expiryDate, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setExpiryDate(expiryDate);
        List<Resource<GiftVoucher>> vouchers = service.getVoucherByExpiryDate(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(GiftVoucherController.class)
                        .getVoucherByExpiryDate(expiryDate, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{customerId}/customer", method = RequestMethod.GET)
    public Resources<Resource<GiftVoucher>> getVoucherByCustomer(@PathVariable String customerId, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setCustomerId(customerId);
        List<Resource<GiftVoucher>> vouchers = service.getVoucherByCustomer(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(GiftVoucherController.class)
                        .getVoucherByCustomer(customerId, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{product}/product", method = RequestMethod.GET)
    public Resources<Resource<GiftVoucher>> getVoucherByProduct(@PathVariable String product, HttpServletRequest request){
        model = new GiftVoucher();
        setCredentials(request, model);
        model.setProductId(product);
        List<Resource<GiftVoucher>> vouchers = service.getVoucherByProduct(model)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(vouchers,
                linkTo(methodOn(GiftVoucherController.class)
                        .getVoucherByCustomer(product, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public Resource<String> addBalance(@PathVariable("code") String code, @RequestBody Payment payment, HttpServletRequest request){
        model.setAmount(payment.getAmount());
        return new Resource("Payment Not Available");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/distribute", method = RequestMethod.POST)
    public Response distribute(@RequestBody GiftVoucher voucher, HttpServletRequest request){
        distributionService.sendStandaloneVoucher(voucher);
        return new Response(HttpStatus.OK, "Voucher Sent Successfully", null );
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/distribute-bulk", method = RequestMethod.POST)
    public Response distributeBulk(@RequestBody List<GiftVoucher> vouchers, HttpServletRequest request){
        distributionService.sendBulkVoucher(vouchers);
        return new Response(HttpStatus.OK, "Vouchers Sent Successfully", null );
    }

    private void setCredentials(HttpServletRequest request, GiftVoucher model) {
        model.setMerchantId(String.valueOf(request.getSession().getAttribute("merchantId")));
        model.setUserId((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
