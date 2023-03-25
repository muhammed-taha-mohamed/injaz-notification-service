package com.injaz_notification_service.controller;

import com.injaz_notification_service.dto.ResponseDto.ResponsePayload;
import com.injaz_notification_service.dto.WhatsAppRequest;
import com.injaz_notification_service.service.WhatsAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("/whatsapp")
@RestController
public class whatsappSenderController {


    private final WhatsAppService service;

    @PostMapping("/send")
    public ResponseEntity<ResponsePayload> sendWhatsAppMessage(@RequestBody WhatsAppRequest request) {
        service.sendWhatsAppMessage(request.getTo(), request.getBody());
        Map<String,String> content = new HashMap<>();
        content.put("message","message is send successfully");
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(content)
                .build()
        );
    }
}
