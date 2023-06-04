package com.trust.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchFilterDTO {
    private String brandId;
    private String name;
    private String NameAr;
    private Boolean active;

    private int page;
    private int size;

}
