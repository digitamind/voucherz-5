package com.interswitch.voucherz.notificationservice.model;


import java.io.Serializable;

public class VerificationMail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String recipientAddress;
    private String message;
    private String subject;

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
