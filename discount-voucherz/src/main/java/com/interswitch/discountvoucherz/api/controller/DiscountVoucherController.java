package com.interswitch.discountvoucherz.api.controller;

import com.interswitch.discountvoucherz.api.controller.util.VoucherResourceAssembler;
import com.interswitch.discountvoucherz.api.model.Response;
import com.interswitch.discountvoucherz.api.model.request.*;
import com.interswitch.discountvoucherz.api.service.AuditTrailService;
import com.interswitch.discountvoucherz.api.service.DiscountVoucherService;
import com.interswitch.discountvoucherz.api.service.DistributionService;
import com.interswitch.discountvoucherz.api.util.EventType;
import com.interswitch.discountvoucherz.api.util.Auth;
import com.interswitch.discountvoucherz.api.util.LogHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/discount-voucher")
@Slf4j
public class
DiscountVoucherController {
    @Autowired
    private DiscountVoucherService<DiscountVoucher> service;

    @Autowired
    private VoucherResourceAssembler assembler;

    private DiscountVoucher requestModel;

    @Autowired
    private DistributionService distributionService;

    private DiscountVoucher responseModel;

    private Response baseResponse;

    @Autowired
    private AuditTrailService auditTrailService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Response> createBulk(@RequestBody BulkVouchers bulkVouchers, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = bulkVouchers.getDiscountVoucher();
        Auth.setCredentials(request, requestModel);
        bulkVouchers.setDiscountVoucher(requestModel);
        baseResponse = service.createBulk(bulkVouchers);
        LogHelper.logResponse(log, baseResponse.getHttpStatus(), baseResponse.toString());
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public ResponseEntity<Response> createSingleVoucher(@PathVariable("code") String code, @RequestBody DiscountVoucher discountVoucher, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        Auth.setCredentials(request, discountVoucher);
        discountVoucher.setCode(code);
        auditTrailService.publishAudit(discountVoucher.getUserId(), EventType.VOUCHER_CREATED,
                "Single discountVoucher generated");
        baseResponse = service.createSingleVoucher(discountVoucher);
        LogHelper.logResponse(log, baseResponse.getHttpStatus(), baseResponse.toString());
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public Resource<DiscountVoucher> getVoucherByCode(@PathVariable("code") String code, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCode(code);
        responseModel = service.getVoucherByCode(requestModel);
        LogHelper.logResponse(log, HttpStatus.OK, responseModel.toString());
        return assembler.toResource(responseModel);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Resources<Resource<DiscountVoucher>> getVoucherByMerchantUser(HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        List<Resource<DiscountVoucher>> vouchers = service.getVoucherByMerchantUser(requestModel)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        LogHelper.logResponse(log, HttpStatus.OK, vouchers.toString());
        return new Resources<>(vouchers,
                linkTo(methodOn(DiscountVoucherController.class).getVoucherByMerchantUser(request)).withSelfRel());
    }

    @RequestMapping(value = "/{code}/update", method = RequestMethod.PUT)
    public ResponseEntity<Response> updateVoucher(@PathVariable("code") String code, @RequestBody UpdateVoucherDetails voucherUpdate, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setIsActive(voucherUpdate.getIsActive());
        requestModel.setCode(code);
        requestModel.setAdditionalInfo(voucherUpdate.getAdditionalInfo());
        requestModel.setExpiryDate(voucherUpdate.getExpiryDate());
        baseResponse = service.updateValue(requestModel);
        LogHelper.logResponse(log, baseResponse.getHttpStatus(), baseResponse.toString());
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @RequestMapping(value = "/{code}/update-value", method = RequestMethod.PUT)
    public ResponseEntity<Response> updateVoucherValue(@PathVariable("code") String code, @RequestBody UpdateVoucherValue voucherValue, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCode(code);
        requestModel.setValue(voucherValue.getValue());
        baseResponse = service.updateValue(requestModel);
        LogHelper.logResponse(log, baseResponse.getHttpStatus(), baseResponse.toString());
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @RequestMapping(value = "/{code}/enable", method = RequestMethod.PUT)
    public ResponseEntity<Response> enable(@PathVariable("code") String code, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCode(code);
        baseResponse = service.enable(requestModel);
        LogHelper.logResponse(log, baseResponse.getHttpStatus(), baseResponse.toString());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}/disable", method = RequestMethod.PUT)
    public ResponseEntity<Response> disable(@PathVariable("code") String code, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCode(code);
        baseResponse = service.disable(requestModel);
        LogHelper.logResponse(log, baseResponse.getHttpStatus(), baseResponse.toString());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}/validate", method = RequestMethod.POST)
    public Resource<DiscountVoucher> validateVoucher(@PathVariable("code") String code, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCode(code);
        responseModel = service.validateVoucher(requestModel);
        LogHelper.logResponse(log, HttpStatus.OK, responseModel.toString());
        return assembler.toResource(responseModel);
    }

    @RequestMapping(value = "/{code}/redeem", method = RequestMethod.POST)
    public Resource<DiscountVoucher> redeem(@PathVariable("code") String code, @RequestBody RedeemVoucher redeemVoucher, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCode(code);
        responseModel = service.redeem(requestModel);
        LogHelper.logResponse(log, HttpStatus.OK, responseModel.toString());
        return assembler.toResource(responseModel);
    }

    @RequestMapping(value = "/{code}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Response> delete(@PathVariable("code") String code, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCode(code);
        baseResponse = service.delete(requestModel);
        LogHelper.logResponse(log, HttpStatus.OK, baseResponse.toString());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{code}/undelete", method = RequestMethod.PUT)
    public ResponseEntity<Response> unDelete(@PathVariable("code") String code, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCode(code);
        baseResponse = service.unDelete(requestModel);
        LogHelper.logResponse(log, HttpStatus.OK, baseResponse.toString());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{campaignId}/campaign", method = RequestMethod.GET)
    public Resources<Resource<DiscountVoucher>> getVoucherByCampaign(@PathVariable String campaignId, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCampaignId(campaignId);
        List<Resource<DiscountVoucher>> vouchers = service.getVoucherByCampaign(requestModel)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        LogHelper.logResponse(log, HttpStatus.OK, vouchers.toString());
        return new Resources<>(vouchers,
                linkTo(methodOn(DiscountVoucherController.class)
                        .getVoucherByCode(requestModel.getCode(), null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{date}/date_created", method = RequestMethod.GET)
    public Resources<Resource<DiscountVoucher>> getVoucherByDateCreated(@PathVariable("date") String date, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setDateCreated(LocalDate.parse(date));
        List<Resource<DiscountVoucher>> vouchers = service.getVoucherByDateCreated(requestModel)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        LogHelper.logResponse(log, HttpStatus.OK, vouchers.toString());
        return new Resources<>(vouchers,
                linkTo(methodOn(DiscountVoucherController.class)
                        .getVoucherByDateCreated(date, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{status}/status", method = RequestMethod.GET)
    public Resources<Resource<DiscountVoucher>> getVoucherByActiveStatus(@PathVariable Boolean status, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setIsActive(status);
        List<Resource<DiscountVoucher>> vouchers = service.getVoucherByActiveStatus(requestModel)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        LogHelper.logResponse(log, HttpStatus.OK, vouchers.toString());
        return new Resources<>(vouchers,
                linkTo(methodOn(DiscountVoucherController.class)
                        .getVoucherByActiveStatus(status, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{expiryDate}/expiry_date", method = RequestMethod.GET)
    public Resources<Resource<DiscountVoucher>> getVoucherByExpiryDate(@PathVariable("expiryDate") String expiryDate, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setExpiryDate(expiryDate);
        List<Resource<DiscountVoucher>> vouchers = service.getVoucherByExpiryDate(requestModel)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        LogHelper.logResponse(log, HttpStatus.OK, vouchers.toString());
        return new Resources<>(vouchers,
                linkTo(methodOn(DiscountVoucherController.class)
                        .getVoucherByExpiryDate(expiryDate, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{customerId}/customer", method = RequestMethod.GET)
    public Resources<Resource<DiscountVoucher>> getVoucherByCustomer(@PathVariable String customerId, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setCustomerId(customerId);
        List<Resource<DiscountVoucher>> vouchers = service.getVoucherByCustomer(requestModel)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        LogHelper.logResponse(log, HttpStatus.OK, vouchers.toString());
        return new Resources<>(vouchers,
                linkTo(methodOn(DiscountVoucherController.class)
                        .getVoucherByCustomer(customerId, null))
                        .withSelfRel());
    }

    @RequestMapping(value = "/{productId}/product", method = RequestMethod.GET)
    public Resources<Resource<DiscountVoucher>> getVoucherByProduct(@PathVariable("productId") String productId, HttpServletRequest request){
        LogHelper.logRequest(request, log);
        requestModel = new DiscountVoucher();
        Auth.setCredentials(request, requestModel);
        requestModel.setProductId(productId);
        List<Resource<DiscountVoucher>> vouchers = service.getVoucherByProduct(requestModel)
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        LogHelper.logResponse(log, HttpStatus.OK, vouchers.toString());
        return new Resources<>(vouchers,
                linkTo(methodOn(DiscountVoucherController.class)
                        .getVoucherByProduct(productId, null))
                        .withSelfRel());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "{code}/distribute", method = RequestMethod.POST)
    public Response distribute(@PathVariable("code") String code, HttpServletRequest request){
        requestModel = new DiscountVoucher();
        requestModel.setCode(code);
        Auth.setCredentials(request, requestModel);
        DiscountVoucher voucher = service.getVoucherByCode(requestModel);
        distributionService.sendStandaloneVoucher(voucher);
        return new Response(HttpStatus.OK, "Voucher Sent Successfully", null );
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/distribute-bulk", method = RequestMethod.POST)
    public Response distributeBulk(@RequestBody List<DiscountVoucher> vouchers, HttpServletRequest request){
        distributionService.sendBulkVoucher(vouchers);
        return new Response(HttpStatus.OK, "Vouchers Sent Successfully", null );
    }
}