package com.interswitch.voucherz.notificationservice.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String ...templateArgs);
    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
    void prepareAndSend(String recipient, String message);
}
