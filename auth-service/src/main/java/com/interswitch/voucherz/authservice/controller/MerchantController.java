package com.interswitch.voucherz.authservice.controller;

import com.interswitch.voucherz.authservice.Exception.CustomException;
import com.interswitch.voucherz.authservice.controller.model.*;
import com.interswitch.voucherz.authservice.models.*;
import com.interswitch.voucherz.authservice.queue.producer.AuditEventPublisher;
import com.interswitch.voucherz.authservice.service.AuditTrailService;
import com.interswitch.voucherz.authservice.service.LoginService;
import com.interswitch.voucherz.authservice.service.MerchantService;
import com.interswitch.voucherz.authservice.service.VerificationTokenService;
import com.interswitch.voucherz.authservice.util.EventType;
import com.interswitch.voucherz.authservice.util.LogHelper;
import com.interswitch.voucherz.authservice.util.VerificationMailType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    MerchantService merchantService;

    @Autowired
    LoginService loginService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    AuditTrailService auditTrailService;

    public MerchantController(MerchantService merchantService){
        this.merchantService = merchantService;
    }

    @RequestMapping(value="/signup",  method = RequestMethod.POST)
    public Response signUp(@RequestBody MerchantUser merchantUser, HttpServletRequest request){
        LogHelper.logRequest(request, logger);

        Response response = null;
        merchantUser.setMerchantId(null);
        merchantUser.setRole("ADMIN");

        if(merchantService.findMerchantByUsername(merchantUser.getEmail()) != null){

            Response responseConflict = new Response(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), "Username already exist", null);
            LogHelper.logResponse(logger, HttpStatus.CONFLICT, responseConflict);
            return responseConflict;

        }

        merchantService.createMerchant(merchantUser);

        verificationTokenService.sendVerificationToken(merchantUser,
                VerificationMailType.REGISTRATION_VERFICATION_MAIL);

        auditTrailService.publishAudit(merchantUser.getEmail(), EventType.MERCHANT_CREATED,
                "Merchant Created");

        response = new Response(HttpStatus.CREATED, HttpStatus.CREATED.value(),"User created",null);
        LogHelper.logResponse(logger, response.getStatus(), response);
        return response;

    }

    @RequestMapping(value = "/confirmRegistration", method=RequestMethod.GET)
    public ModelAndView confirmRegistration(@RequestParam("token") String token, HttpServletRequest request){
        LogHelper.logRequest(request, logger);

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null){
            LogHelper.logResponse(logger, HttpStatus.FORBIDDEN, null);
            return new ModelAndView("redirect:"
                    + "http://localhost:3000/v1/merchant-management/merchants/error");
        }

        MerchantUser user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            LogHelper.logResponse(logger, HttpStatus.FORBIDDEN, null);
            return new ModelAndView("redirect:"
                    + "http://localhost:3000/v1/merchant-management/merchants/expire");
        }

        merchantService.changeMerchantStatus(user, true);

        verificationTokenService.deleteVerificationToken(verificationToken);

        auditTrailService.publishAudit(user.getEmail(), EventType.MERCHANT_CREATED,
                "User confirm registration");

        LogHelper.logResponse(logger, HttpStatus.OK, null);
        return new ModelAndView("redirect:"
                + "http://localhost:3000/v1/merchant-management/merchants/success");
    }




    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest,
                                              HttpServletRequest request) {
        LogHelper.logRequest(request, logger);
        String token = loginService.login(loginRequest.getUsername(),loginRequest.getPassword());

        if(token != null){

            auditTrailService.publishAudit(loginRequest.getUsername(), EventType.MERCHANT_LOGIN,
                    "Merchant logged in");

            HttpHeaders headers = new HttpHeaders();
            List<String> headerlist = new ArrayList<>();
            List<String> exposeList = new ArrayList<>();
            headerlist.add("Content-Type");
            headerlist.add("Accept");
            headerlist.add("X-Requested-With");
            headerlist.add("Authorization");
            headers.setAccessControlAllowHeaders(headerlist);
            exposeList.add("Authorization");
            headers.setAccessControlExposeHeaders(exposeList);
            headers.set("Authorization", token);

            ResponseEntity<AuthResponse> response = new ResponseEntity<AuthResponse>(new AuthResponse(token), headers, HttpStatus.OK);
            LogHelper.logResponse(logger, HttpStatus.OK, response);
            return response;

        }

        ResponseEntity<AuthResponse> errorResponse = new ResponseEntity<AuthResponse>(new AuthResponse(null), null, HttpStatus.UNAUTHORIZED);
        LogHelper.logResponse(logger, HttpStatus.UNAUTHORIZED, errorResponse);
        return errorResponse;


    }


    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<AuthResponse> logout (@RequestHeader(value="Authorization") String token,
                                                HttpServletRequest request) {
        LogHelper.logRequest(request, logger);
        HttpHeaders headers = new HttpHeaders();

        DUserDetail userDetails =
                (DUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loginService.logout(token)) {
            headers.remove("Authorization");

            auditTrailService.publishAudit(userDetails.getUsername(), EventType.MERCHANT_LOGOUT,
                    "Merchant logged out");
            ResponseEntity<AuthResponse> logoutResponse = new ResponseEntity<AuthResponse>(new AuthResponse("logged out"), headers, HttpStatus.OK);
            LogHelper.logResponse(logger, HttpStatus.OK, logoutResponse);
            return logoutResponse;
        }

        ResponseEntity<AuthResponse> logout_failed = new ResponseEntity<AuthResponse>(new AuthResponse("Logout Failed"), headers, HttpStatus.BAD_REQUEST);
        LogHelper.logResponse(logger, HttpStatus.BAD_REQUEST, logout_failed);
        return logout_failed;
    }


    @PostMapping("/valid/token")
    public Boolean isValidToken (@RequestHeader(value="Authorization") String token) {
        return true;
    }


    @PostMapping("/signin/token")
    public ResponseEntity<AuthResponse> createNewToken (@RequestHeader(value="Authorization") String token) {
        String newToken = loginService.createNewToken(token);
        DUserDetail userDetails =
                (DUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(newToken != null){

            auditTrailService.publishAudit(userDetails.getUsername(), EventType.TOKEN_MANAGEMENT,
                    "Created new access token");

            HttpHeaders headers = new HttpHeaders();
            List<String> headerList = new ArrayList<>();
            List<String> exposeList = new ArrayList<>();
            headerList.add("Content-Type");
            headerList.add(" Accept");
            headerList.add("X-Requested-With");
            headerList.add("Authorization");
            headers.setAccessControlAllowHeaders(headerList);
            exposeList.add("Authorization");
            headers.setAccessControlExposeHeaders(exposeList);
            headers.set("Authorization", newToken);
            return new ResponseEntity<AuthResponse>(new AuthResponse(newToken), headers, HttpStatus.CREATED);
        }

        auditTrailService.publishAudit(userDetails.getUsername(), EventType.TOKEN_MANAGEMENT,
                "Failed generating token");

        return new ResponseEntity<AuthResponse>(new AuthResponse(null), null, HttpStatus.FORBIDDEN);

    }

    @RequestMapping(value="/forgot-password", method=RequestMethod.POST)
    public Response forgotPassword(@RequestBody Username username, HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        MerchantUser merchantUser = merchantService.findMerchantByUsername(username.getUsername());

        if (merchantUser == null){
            Response response = new Response(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
                    "Email does not exist", null);
            LogHelper.logResponse(logger, HttpStatus.NOT_FOUND, response);
            return response;
        }

        verificationTokenService.sendVerificationToken(merchantUser,
                VerificationMailType.FORGOT_PASSWORD_VERIFICATION_MAIL);

        auditTrailService.publishAudit(merchantUser.getEmail(), EventType.FORGOT_PASSWORD,
                "User forgot password - code generated");

        Response response = new Response(HttpStatus.OK, HttpStatus.OK.value(),
                "Verification code has been sent to user email", null);
        LogHelper.logResponse(logger, HttpStatus.OK, response);
        return response;
    }

    @RequestMapping(value="/password-reset-redirect", method=RequestMethod.GET)
    public ModelAndView changePasswordRedirect(@RequestParam("token") String token, HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        return new ModelAndView("redirect:"
                + "http://localhost:3000/v1/merchant-management" +
                "/merchants/edit-password?token="+ token);
    }

    @RequestMapping(value = "/password-reset", method = RequestMethod.GET)
    public Response changePassword(@RequestParam("token") String token,
                                   @RequestBody ChangePassword changePassword,
                                   HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())){
            Response response = new Response(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(),
                    "Password and Confirm password not the same", null);
            LogHelper.logResponse(logger, HttpStatus.CONFLICT, response);
            return response;
        }

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null){
            Response response = new Response(HttpStatus.NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE.value(),
                    "Invalid token", null);
            LogHelper.logResponse(logger, HttpStatus.NOT_ACCEPTABLE, response);
            return response;
        }

        MerchantUser user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            Response response = new Response(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value(),
                    "Token is expired", null);
            LogHelper.logResponse(logger, HttpStatus.FORBIDDEN, response);
            return response;
        }

        //change merchantUser password;
        merchantService.changeMerchantUserPassword(user.getEmail(), changePassword.getNewPassword());

        verificationTokenService.deleteVerificationToken(verificationToken);

        auditTrailService.publishAudit(user.getEmail(), EventType.FORGOT_PASSWORD,
                "password successfully changed");

        Response response = new Response(HttpStatus.OK, HttpStatus.OK.value(), "Password successfully changed", null);
        LogHelper.logResponse(logger, HttpStatus.OK, response);
        return response;
    }



    @RequestMapping(value = "/change-password", method=RequestMethod.POST)
    public Response changePassword(@RequestBody ChangePassword changePassword,
                                   HttpServletRequest request){
        LogHelper.logRequest(request, logger);

        DUserDetail userDetails =
                (DUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MerchantUser merchantUser = merchantService.findMerchantByUsername(userDetails.getUsername());

        if (merchantUser == null){
            Response response = new Response(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
                    "Merchant does not exist", null);
            LogHelper.logResponse(logger, HttpStatus.NOT_FOUND, response);
            return response;
        }

        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())){
            Response response = new Response(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(),
                    "Password and Confirm password not the same", null);
            LogHelper.logResponse(logger, HttpStatus.CONFLICT, response);
            return response;
        }

        merchantService.changeMerchantUserPassword(merchantUser.getEmail(),
                changePassword.getNewPassword());

        auditTrailService.publishAudit(merchantUser.getEmail(), EventType.CHANGED_PASSWORD,
                "User changed password");

        Response response = new Response(HttpStatus.OK, HttpStatus.OK.value(), "Password successfully changed", null);
        LogHelper.logResponse(logger, HttpStatus.OK, response);
        return response;


    }

    @RequestMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PagedResources<Merchant>> getAllMerchants(Pageable pageable, PagedResourcesAssembler assembler){
        Link link = new Link("http://localhost:8080/v1/marchant-management-service/merchants");
        Page<Merchant> merchants = merchantService.getAllMerchants(pageable.getPageNumber(), pageable.getPageSize());
        org.springframework.data.domain.Page<Merchant> pageMerchant = new PageImpl<Merchant>(merchants.getContent(),
                new PageRequest(pageable.getPageNumber(), pageable.getPageSize()), merchants.getCount());
        long MerchantCount = merchants.getCount();
        HttpHeaders headers = new HttpHeaders();
        List<String> headerlist = new ArrayList<>();
        List<String> exposeList = new ArrayList<>();
        headers.set("X-Total-Count", Long.toString(MerchantCount));
        headerlist.add("Content-Type");
        headerlist.add("Accept");
        headerlist.add("X-Requested-With");
        headerlist.add("Authorization");
        headerlist.add("X-Total-Count");
        headers.setAccessControlAllowHeaders(headerlist);
        exposeList.add("X-Total-Count");
        headers.setAccessControlExposeHeaders(exposeList);

        return new ResponseEntity < > (assembler.toResource(pageMerchant,
                link.withSelfRel()), headers, HttpStatus.OK);

    }

    @RequestMapping(value="/merchant-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PagedResources<Merchant>> getAllMerchantUsers(Pageable pageable,
                                                                         PagedResourcesAssembler assembler){
        Link link = new
                Link("http://localhost:8080/v1/marchant-management-service/merchants/merchant-user");
        Page<MerchantUser> merchants = merchantService.
                getAllMerchantUsers(pageable.getPageNumber(), pageable.getPageSize());
        org.springframework.data.domain.Page<MerchantUser> pageMerchant = new
                PageImpl<MerchantUser>(merchants.getContent(),
                new PageRequest(pageable.getPageNumber(), pageable.getPageSize()), merchants.getCount());
        long MerchantCount = merchants.getCount();

        HttpHeaders headers = new HttpHeaders();
        List<String> headerlist = new ArrayList<>();
        List<String> exposeList = new ArrayList<>();
        headers.set("X-Total-Count", Long.toString(MerchantCount));
        headerlist.add("Content-Type");
        headerlist.add("Accept");
        headerlist.add("X-Requested-With");
        headerlist.add("Authorization");
        headerlist.add("X-Total-Count");
        headers.setAccessControlAllowHeaders(headerlist);
        exposeList.add("X-Total-Count");
        headers.setAccessControlExposeHeaders(exposeList);

        return new ResponseEntity < > (assembler.toResource(pageMerchant,
                link.withSelfRel()), headers, HttpStatus.OK);

    }

    @RequestMapping(value="listTest", method=RequestMethod.GET)
    public List<Merchant> getAllMerchantTest(@RequestParam("page") int pageNumber,
                                             @RequestParam("size") int pageSize){
        return merchantService.getAllMerchantTest(pageNumber, pageSize);
    }




}
