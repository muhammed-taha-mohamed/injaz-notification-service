package com.trust.model;


import com.trust.dto.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Brand {
    private String id;
    private String BrandKey;
    private String name;
    private String nameAr;
    private String country;
    private String info;
    private String image;
    @Indexed(unique = true)
    private String email;
    private PhoneNumber phoneNumber;
    @DBRef
    private List<User> users;
}
