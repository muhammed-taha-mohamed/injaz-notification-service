package com.trust.service;


import com.trust.dto.LoginDTO;
import com.trust.dto.LoginResponseDTO;
import com.trust.dto.brand.BrandDTO;
import com.trust.dto.brand.CreateBrandDTO;

public interface IBrandService {
    BrandDTO create (CreateBrandDTO dto);
    LoginResponseDTO login (LoginDTO loginDTO);
    BrandDTO findById (String id);

}
