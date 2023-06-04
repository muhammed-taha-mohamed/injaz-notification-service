package com.trust.dto.customer;

import com.trust.dto.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private String id;
    private String customerKey;
    private String name;
    private String email;
    private String image;
    private PhoneNumber phoneNumber;
}
