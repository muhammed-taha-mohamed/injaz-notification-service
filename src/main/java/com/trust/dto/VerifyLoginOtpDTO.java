package com.trust.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VerifyLoginOtpDTO {
    private PhoneNumber phoneNumber;
    private String otp;
}
