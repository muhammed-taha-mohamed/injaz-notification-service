package com.trust.model;


import com.trust.dto.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Customer {
    private String id;
    private String customerKey;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String image;
    private PhoneNumber phoneNumber;
    @DBRef
    private User user;
}
