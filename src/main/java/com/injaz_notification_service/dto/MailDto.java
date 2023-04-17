package com.injaz_notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailDto {
    private String to ;
    private String subject;
    private String text;
}
