package com.trust.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateBranchDTO {
    private String name;
    private String nameAr;
    private String address;
    private String brandId;
}
