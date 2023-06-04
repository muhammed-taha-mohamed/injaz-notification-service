package com.trust.dao.custom;

import com.trust.dto.branch.BranchDTO;
import com.trust.dto.branch.BranchFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchCustomRepository {
    Boolean update(BranchDTO dto);
    Page<BranchDTO> filter (BranchFilterDTO dto);
}
