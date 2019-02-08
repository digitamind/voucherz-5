package com.interswitch.voucherz.audittrailservice.controller;

import com.interswitch.voucherz.audittrailservice.model.AuditEvent;
import com.interswitch.voucherz.audittrailservice.service.AuditTrailService;
import com.interswitch.voucherz.audittrailservice.util.EventType;
import com.interswitch.voucherz.audittrailservice.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/")
public class AuditTrailController {

    @Autowired
    private AuditTrailService auditTrailService;

    @RequestMapping(value = "/audits/{eventType}", method = RequestMethod.GET)
    public ResponseEntity<List<AuditEvent>> getByEventType(@PathVariable("eventType") EventType eventType){
        List<AuditEvent> auditEvents = auditTrailService.findByEventType(eventType);
        return new ResponseEntity<>(auditEvents, HttpStatus.OK);
    }

    @GetMapping(value = "/audits",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <PagedResources< AuditEvent >> AllProducts(Pageable pageable, PagedResourcesAssembler assembler) {
        Link link = new Link("http://localhost:8080/v1/audit-trail/audits");
        Page< AuditEvent > auditEvents = auditTrailService.findAllEvents(pageable);
        PagedResources < AuditEvent > pr = assembler.toResource(auditEvents,
                link.withSelfRel());

        long totalEvents = auditTrailService.getCountAudits();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Link", PaginationUtil.createLinkHeader(pr));
        List<String> headerlist = new ArrayList<>();
        List<String> exposeList = new ArrayList<>();
        headers.set("X-Total-Count", Long.toString(totalEvents));
        headerlist.add("Content-Type");
        headerlist.add("Accept");
        headerlist.add("X-Requested-With");
        headerlist.add("Authorization");
        headerlist.add("X-Total-Count");
        headers.setAccessControlAllowHeaders(headerlist);
        exposeList.add("X-Total-Count");
        headers.setAccessControlExposeHeaders(exposeList);

        return new ResponseEntity < > (assembler.toResource(auditEvents,
                link.withSelfRel()), headers, HttpStatus.OK);
    }






}
