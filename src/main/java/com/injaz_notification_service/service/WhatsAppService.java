package com.injaz_notification_service.service;

import com.injaz_notification_service.error.exceptions.CustomException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.management.RuntimeMBeanException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class WhatsAppService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.from-phone-number}")
    private String fromNumber;

    public Boolean sendWhatsAppMessage(String to, String body) {
        try {
            Twilio.init(accountSid, authToken);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("whatsapp:+" + to),
                            new com.twilio.type.PhoneNumber("whatsapp:+" + fromNumber),
                            body)
                    .create();
            return true;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
