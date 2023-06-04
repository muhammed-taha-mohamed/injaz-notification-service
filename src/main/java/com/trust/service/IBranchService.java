package com.trust.service;


import com.trust.dto.BaseDTO;
import com.trust.dto.branch.BranchDTO;
import com.trust.dto.branch.BranchFilterDTO;
import com.trust.dto.branch.CreateBranchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBranchService {
    BranchDTO create (CreateBranchDTO dto);
    Boolean update (BranchDTO dto);
    Boolean delete(String id);
    BranchDTO findById (String id);
    Page<BranchDTO> filter(BranchFilterDTO filterDTO);
    List<BaseDTO> abstractedByBrand(String brandId);
}
