package com.trust.dto.customer;

import com.trust.dto.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CustomerLoginDTO {
    private PhoneNumber phoneNumber;
    private String password;
}
