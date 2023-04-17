package com.injaz_notification_service.service;

import com.injaz_notification_service.error.exceptions.CustomException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class notifyService {
    private final JavaMailSender emailSender;
    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;
    @Value("${twilio.from-phone-number-whatsapp}")
    private String whatsappFromNumber;

    @Value("${twilio.from-phone-number-sms}")
    private String smsFromNumber;


    public Boolean sendWhatsAppMessage(String to, String body) {
        try {
            Twilio.init(accountSid, authToken);
            Message.creator(
                            new com.twilio.type.PhoneNumber("whatsapp:+" + to),
                            new com.twilio.type.PhoneNumber("whatsapp:+" + whatsappFromNumber),
                            body)
                    .create();
            return true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Boolean sendSmsMessage(String to, String body) {
        try {
            Twilio.init(accountSid, authToken);
            Message.creator(
                            new com.twilio.type.PhoneNumber("+" + to),
                            new com.twilio.type.PhoneNumber("+" + smsFromNumber),
                            body)
                    .create();
            return true;
        } catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
