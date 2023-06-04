package com.trust.dto.notification;

import com.trust.dto.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private PhoneNumber to;
    private String body;

}

