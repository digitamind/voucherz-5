package com.interswitch.voucherz.authservice.controller;

import com.interswitch.voucherz.authservice.Exception.CustomException;
import com.interswitch.voucherz.authservice.controller.model.CampaignRequest;
import com.interswitch.voucherz.authservice.controller.model.Response;
import com.interswitch.voucherz.authservice.models.Campaign;
import com.interswitch.voucherz.authservice.models.CampaignVoucherDelete;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.queue.producer.CampaignDiscountDeletePublisher;
import com.interswitch.voucherz.authservice.queue.producer.CampaignGiftDeletePublisher;
import com.interswitch.voucherz.authservice.queue.producer.CampaignValueDeletePublisher;
import com.interswitch.voucherz.authservice.service.AuditTrailService;
import com.interswitch.voucherz.authservice.service.CampaignService;
import com.interswitch.voucherz.authservice.service.LoginService;
import com.interswitch.voucherz.authservice.service.MerchantService;
import com.interswitch.voucherz.authservice.util.EventType;
import com.interswitch.voucherz.authservice.util.LogHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class CampaignController {
    private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);
    @Autowired
    CampaignService campaignService;

    @Autowired
    LoginService loginService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    CampaignValueDeletePublisher campaignValueDeletePublisher;

    @Autowired
    CampaignGiftDeletePublisher campaignGiftDeletePublisher;

    @Autowired
    CampaignDiscountDeletePublisher campaignDiscountDeletePublisher;

    @Autowired
    AuditTrailService auditTrailService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/campaigns",  method = RequestMethod.POST)
    public Response createCampaign(@RequestHeader("Authorization") String bearerToken,
                                   @RequestBody CampaignRequest campaignRequest,
                                   HttpServletRequest request){
        LogHelper.logRequest(request, logger);

        String merchantUserUsername = loginService.getClaimFromToken(bearerToken);
        MerchantUser merchantUser = merchantService.findMerchantByUsername(merchantUserUsername);

        Campaign campaignCheckIfExist = campaignService.findCampaignByName
                (campaignRequest.getName(), merchantUser.getMerchantId());
        if(campaignCheckIfExist != null){

            Response response = new Response(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(),
                    "Campaign Name already exist", null);
            LogHelper.logResponse(logger, HttpStatus.CONFLICT, response);
            return response;

        }

        Campaign campaign = new Campaign();
        campaign.setName(campaignRequest.getName());
        campaign.setDescription(campaignRequest.getDescription());
        campaign.setActivate(campaignRequest.getActivate());
        campaign.setStartDate(campaignRequest.getStartDate());
        campaign.setExpirationDate(campaignRequest.getExpirationDate());
        campaign.setVoucherType(campaignRequest.getVoucherType());

        Long merchantUserId = merchantUser.getId();
        campaign.setMerchantUserId(merchantUserId);
        campaign.setMerchantId(merchantUser.getMerchantId());

        Campaign campaignCreated = campaignService.createCampaign(campaign);

        Response response = new Response(HttpStatus.CREATED, HttpStatus.CREATED.value(),
                Long.toString(campaign.getId()), null);
        LogHelper.logResponse(logger, HttpStatus.CREATED, response);
        return response;
    }

    @RequestMapping(value = "/campaigns/{name}", method = RequestMethod.GET)
    public Campaign getCampaign(@RequestHeader("Authorization") String bearerToken,
                                @PathVariable("name") String name,
                                HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        String merchantUserUsername = loginService.getClaimFromToken(bearerToken);
        MerchantUser merchantUser = merchantService.findMerchantByUsername(merchantUserUsername);

        Campaign campaignCheckIfExist = campaignService.findCampaignByName
                (name, merchantUser.getMerchantId());
        if(campaignCheckIfExist == null){
            LogHelper.logResponse(logger, HttpStatus.NOT_FOUND, null);
            throw new CustomException
                    ("Campaign does not exist", HttpStatus.NOT_FOUND);
        }
        LogHelper.logResponse(logger, HttpStatus.FOUND, null);
        return campaignCheckIfExist;
    }

    @RequestMapping(value = "/campaigns/{name}", method = RequestMethod.PUT)
    public Campaign updateCampaign(@RequestHeader("Authorization") String bearerToken,
                                   @PathVariable("name") String name,
                                   @RequestBody Campaign updatedCampaign,
                                   HttpServletRequest request){

        LogHelper.logRequest(request, logger);

        String merchantUserUsername = loginService.getClaimFromToken(bearerToken);
        MerchantUser merchantUser = merchantService.findMerchantByUsername(merchantUserUsername);

        Campaign campaign = campaignService.findCampaignByName(name, merchantUser.getMerchantId());
        if(campaign == null){
            LogHelper.logResponse(logger, HttpStatus.NOT_FOUND, null);
            throw new CustomException("Campaign does not exist", HttpStatus.NOT_FOUND);
        }
        LogHelper.logResponse(logger, HttpStatus.OK, null);
        return campaignService.updateCampaign(updatedCampaign);
    }

    @RequestMapping(value = "/campaigns/{name}", method = RequestMethod.DELETE)
    public Response deleteCampaign(@RequestHeader("Authorization") String bearerToken,
                                   @PathVariable("name") String name,
                                   HttpServletRequest request){

        LogHelper.logRequest(request, logger);

        String merchantUserUsername = loginService.getClaimFromToken(bearerToken);
        MerchantUser merchantUser = merchantService.findMerchantByUsername(merchantUserUsername);

        Campaign campaignCheckIfExist = campaignService.findCampaignByName
                (name, merchantUser.getMerchantId());
        if(campaignCheckIfExist == null){
            LogHelper.logResponse(logger, HttpStatus.NOT_FOUND, null);
            throw new CustomException
                    ("Campaign does not exist", HttpStatus.NOT_FOUND);
        }

        campaignService.deleteCampaign(name, merchantUser.getMerchantId());

        auditTrailService.publishAudit(merchantUser.getEmail(), EventType.DELETE_CAMPAIGN,
                "Deleted campaign " + campaignCheckIfExist.getId());

        int voucherType = campaignCheckIfExist.getVoucherType();
        CampaignVoucherDelete campaignVoucherDelete = new CampaignVoucherDelete();
        campaignVoucherDelete.setCampaignId(campaignCheckIfExist.getId());
        campaignVoucherDelete.setMerchantId(merchantUser.getMerchantId());
        campaignVoucherDelete.setVoucherType(voucherType);

        switch (voucherType){
            case 1:
                campaignGiftDeletePublisher.publish(campaignVoucherDelete);
                break;
            case 2:
                campaignDiscountDeletePublisher.publish(campaignVoucherDelete);
                break;
            case 3:
                campaignValueDeletePublisher.publish(campaignVoucherDelete);

        }
        LogHelper.logResponse(logger, HttpStatus.OK, null);
        return new Response(HttpStatus.OK,HttpStatus.OK.value(), "Successfully deleted", null);
    }

    @RequestMapping(value = "/campaigns", method = RequestMethod.GET)
    public List<Campaign> getAllCampaign(@RequestHeader("Authorization") String bearerToken,
                                         HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        String merchantUserUsername = loginService.getClaimFromToken(bearerToken);
        MerchantUser merchantUser = merchantService.findMerchantByUsername(merchantUserUsername);

        LogHelper.logResponse(logger, HttpStatus.OK, null);
        return campaignService.findAllCampaign(merchantUser.getMerchantId());
    }

}
