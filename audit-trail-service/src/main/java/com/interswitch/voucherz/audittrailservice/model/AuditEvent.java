package com.interswitch.voucherz.audittrailservice.model;

import com.interswitch.voucherz.audittrailservice.util.EventType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.core.Relation;

import java.util.Date;


@Relation(value = "data", collectionRelation ="data")
@Document(collection = "AuditEvent")

public class AuditEvent {
    @Id
    private String id;
    private Date eventDate;
    private EventType eventType;
    private String username;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
