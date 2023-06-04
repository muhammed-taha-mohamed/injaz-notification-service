package com.trust.dto.brand;

import com.trust.dto.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class CreateBrandDTO {
    private String name;
    private String nameAr;
    private String country;
    private String info;
    private String image;
    @Email(message = "{email.not.valid}")
    private String email;
    private PhoneNumber phoneNumber;
    private String password;

}
