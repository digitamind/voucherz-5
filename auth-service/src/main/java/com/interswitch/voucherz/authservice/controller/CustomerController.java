package com.interswitch.voucherz.authservice.controller;
import com.interswitch.voucherz.authservice.controller.model.CustomerRequest;
import com.interswitch.voucherz.authservice.controller.model.Response;
import com.interswitch.voucherz.authservice.models.Customer;
import com.interswitch.voucherz.authservice.models.DUserDetail;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.service.AuditTrailService;
import com.interswitch.voucherz.authservice.service.CustomerService;
import com.interswitch.voucherz.authservice.service.LoginService;
import com.interswitch.voucherz.authservice.service.MerchantService;
import com.interswitch.voucherz.authservice.util.CsvReadUtil;
import com.interswitch.voucherz.authservice.util.EventType;
import com.interswitch.voucherz.authservice.util.LogHelper;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    LoginService loginService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    AuditTrailService auditTrailService;

    @RequestMapping(value="/customers",  method = RequestMethod.POST)
    public Response createCustomer(@RequestBody CustomerRequest customerRequest,
                                   HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        DUserDetail userDetails =
                (DUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MerchantUser merchantUser = merchantService.findMerchantByUsername(userDetails.getUsername());

        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAddress(customerRequest.getAddress());
        customer.setCity(customerRequest.getCity());
        customer.setCountry(customerRequest.getCountry());
        customer.setPhoneNo(customerRequest.getPhoneNumber());
        customer.setEmail(customerRequest.getEmail());
        customer.setAmountOfOrders(customerRequest.getAmountOfOrders());
        customer.setNumberOfOrders(customerRequest.getNumberOfOrders());
        customer.setDateJoined(customerRequest.getDateJoined());
        customer.setLastOrderAmount(customer.getLastOrderAmount());
        customer.setLastOrderDate(customer.getLastOrderDate());
        customer.setKpi(customer.getKpi());
        customer.setMerchantId(merchantUser.getMerchantId());

        customerService.createCustomer(customer);

        auditTrailService.publishAudit(merchantUser.getEmail(), EventType.CUSTOMER_CREATED,
                "Customer was created by merchant");

        LogHelper.logResponse(logger, HttpStatus.CREATED, null);
        return new Response(HttpStatus.CREATED, HttpStatus.CREATED.value(), "Customer created", null);
    }

    @RequestMapping(value="/customers/{guid}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("guid") String guid,
                                HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        DUserDetail userDetails =
                (DUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MerchantUser merchantUser = merchantService.findMerchantByUsername(userDetails.getUsername());

        auditTrailService.publishAudit(merchantUser.getEmail(), EventType.CRUD_CUSTOMER,
                "Getting customer details");

        LogHelper.logResponse(logger, HttpStatus.FOUND, null);
        return customerService.getCustomerById(guid, merchantUser.getMerchantId());
    }

    @RequestMapping(value="/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        DUserDetail userDetails =
                (DUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info(userDetails.getUsername());
        MerchantUser merchantUser = merchantService.findMerchantByUsername(userDetails.getUsername());

        auditTrailService.publishAudit(merchantUser.getEmail(), EventType.CRUD_CUSTOMER,
                "Getting customers details");

        LogHelper.logResponse(logger, HttpStatus.FOUND, null);
        return customerService.getAllCustomers(merchantUser.getMerchantId());
    }

    @RequestMapping(value = "/customers/{guid}", method = RequestMethod.DELETE)
    public Response deleteCustomer(@PathVariable("guid") String guid, HttpServletRequest request){
        LogHelper.logRequest(request, logger);

        DUserDetail userDetails =
                (DUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MerchantUser merchantUser = merchantService.findMerchantByUsername(userDetails.getUsername());

        customerService.deleteCustomer(guid,merchantUser.getMerchantId());

        auditTrailService.publishAudit(merchantUser.getEmail(), EventType.CRUD_CUSTOMER,
                "Deleted Customer");
        LogHelper.logResponse(logger, HttpStatus.OK, null);
        return new Response(HttpStatus.OK, HttpStatus.OK.value(), "Successfully deleted", null);
    }

    @RequestMapping(value = "/customers/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public Response uploadCustomersCsv(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        LogHelper.logRequest(request, logger);
        DUserDetail userDetails =
                (DUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MerchantUser merchantUser = merchantService.findMerchantByUsername(userDetails.getUsername());
        try {
            customerService.insertCustomers(CsvReadUtil.read(Customer.class, file.getInputStream()), merchantUser.getMerchantId());
            auditTrailService.publishAudit(merchantUser.getEmail(), EventType.CRUD_CUSTOMER,
                    "Uploaded customer");
        } catch (SQLServerException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        LogHelper.logResponse(logger, HttpStatus.CREATED, null);
        return new Response(HttpStatus.CREATED, HttpStatus.CREATED.value(), "File successfully uploaded and customers created", null);
    }

}
