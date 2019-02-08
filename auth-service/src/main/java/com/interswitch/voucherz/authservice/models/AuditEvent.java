package com.interswitch.voucherz.authservice.models;

import com.interswitch.voucherz.authservice.util.EventType;
import java.util.Date;

public class AuditEvent {
    private Date eventDate;
    private EventType eventType;
    private String username;
    private String description;

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
