package com.trust.dto.brand;

import com.trust.dto.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class BrandDTO {
    private String id;
    private String BrandKey;
    private String name;
    private String nameAr;
    private String country;
    private String info;
    private String image;
    private String email;
    private PhoneNumber phoneNumber;
}
