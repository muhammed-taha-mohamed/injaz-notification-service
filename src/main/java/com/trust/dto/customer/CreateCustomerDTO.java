package com.trust.dto.customer;
import com.trust.dto.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCustomerDTO {
    private String name;
    @Email(message = "{email.not.valid}")
    private String email;
    private PhoneNumber phoneNumber;
    private String password;
}
