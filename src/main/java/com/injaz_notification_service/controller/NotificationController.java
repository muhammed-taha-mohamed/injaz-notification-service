package com.injaz_notification_service.controller;

import com.injaz_notification_service.dto.MailDto;
import com.injaz_notification_service.dto.ResponseDto.ResponsePayload;
import com.injaz_notification_service.dto.MessageRequest;
import com.injaz_notification_service.service.notifyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class NotificationController {
    private final notifyService service;

    @PostMapping("/whatsapp/send")
    public ResponseEntity<ResponsePayload> sendWhatsAppMessage(@RequestBody MessageRequest request) {
        service.sendWhatsAppMessage(request.getTo().getCountryCode()+request.getTo().getNumber(), request.getBody());
        Map<String,String> content = new HashMap<>();
        content.put("message","Message is send successfully");
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(content)
                .build()
        );
    }

    @PostMapping("/sms/send")
    public ResponseEntity<ResponsePayload> sendSmsMessage(@RequestBody MessageRequest request) {
        service.sendSmsMessage(request.getTo().getCountryCode()+request.getTo().getNumber(), request.getBody());
        Map<String,String> content = new HashMap<>();
        content.put("message","Message is send successfully");
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(content)
                .build()
        );
    }

    @PostMapping("/email/send-text")
    public ResponseEntity<ResponsePayload> sendSimpleEmail(@RequestBody MailDto mailDto) {
        service.sendSimpleMessage(mailDto.getTo(), mailDto.getSubject(), mailDto.getText());
        Map<String,String> content = new HashMap<>();
        content.put("message","Email is send successfully");
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(content)
                .build()
        );
    }
}
