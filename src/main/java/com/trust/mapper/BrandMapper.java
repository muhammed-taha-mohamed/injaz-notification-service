package com.trust.mapper;

import com.trust.dto.brand.BrandDTO;
import com.trust.dto.brand.CreateBrandDTO;
import com.trust.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface BrandMapper {

    Brand toEntity(CreateBrandDTO dto);
    BrandDTO toDto (Brand brand);


}
