package com.trust.dto.branch;

import com.trust.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BranchDTO {
    private String id;
    private String name;
    private String nameAr;
    private Boolean active;
    private String Address;
    private Brand brand;

}
