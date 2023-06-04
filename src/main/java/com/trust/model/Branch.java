package com.trust.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Branch {
    private String id;
    private String name;
    private String nameAr;
    private Boolean active = true;
    private String Address;
    @DBRef
    private Brand brand;
}