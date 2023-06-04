package com.trust.model;


import com.trust.dto.PhoneNumber;
import com.trust.enummeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@Builder
public class User {
    private String id;
    private String name;
    private String password;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private PhoneNumber phoneNumber;
    private Role role;
    private String image;
    private Boolean active;
    private String loginOtp;
}
