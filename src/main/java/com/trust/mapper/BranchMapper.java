package com.trust.mapper;

import com.trust.dto.branch.BranchDTO;
import com.trust.dto.branch.CreateBranchDTO;
import com.trust.dto.brand.BrandDTO;
import com.trust.dto.brand.CreateBrandDTO;
import com.trust.model.Branch;
import com.trust.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" )
public interface BranchMapper {

    @Mapping(source = "brandId" ,  target = "brand.id")
    Branch toEntity(CreateBranchDTO dto);
    BranchDTO toDto (Branch branch);

    default Page<BranchDTO> toDtoPages(Page<Branch>  branches) {
        return branches.map(branch -> this.toDto(branch));
    }

    default List<BranchDTO> toDtoList(List<Branch>  branches) {
        return branches.stream().map(branch -> this.toDto(branch)).collect(Collectors.toList());
    }

}
