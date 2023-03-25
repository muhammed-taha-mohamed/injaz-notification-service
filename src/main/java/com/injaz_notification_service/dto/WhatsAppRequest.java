package com.injaz_notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhatsAppRequest {

    private String to;
    private String body;


}

