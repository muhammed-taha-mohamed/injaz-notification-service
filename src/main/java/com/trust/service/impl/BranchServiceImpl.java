package com.trust.service.impl;

import com.trust.dao.BranchRepository;
import com.trust.dao.custom.BranchCustomRepository;
import com.trust.dto.BaseDTO;
import com.trust.dto.branch.BranchDTO;
import com.trust.dto.branch.BranchFilterDTO;
import com.trust.dto.branch.CreateBranchDTO;
import com.trust.error.exceptions.AbstractTrustException;
import com.trust.mapper.BranchMapper;
import com.trust.model.Branch;
import com.trust.service.IBranchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BranchServiceImpl implements IBranchService {
    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;
    private final BranchCustomRepository customRepository;

    @Override
    public BranchDTO create(CreateBranchDTO dto) {
        try {
            Branch branch = branchRepository.save(branchMapper.toEntity(dto));
            return branchMapper.toDto(branch);
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    @Override
    public Boolean update(BranchDTO dto) {
        try {
            return customRepository.update(dto);
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    @Override
    public Boolean delete(String id) {
        try {
            branchRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    @Override
    public BranchDTO findById(String id) {
        try {
            Branch branch = branchRepository.findById(id).orElseThrow(() ->
                    new AbstractTrustException("Not found")
            );
            return branchMapper.toDto(branch);
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    @Override
    public Page<BranchDTO> filter (BranchFilterDTO filterDTO) {
        try {
            return customRepository.filter(filterDTO);
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    @Override
    public List<BaseDTO> abstractedByBrand(String brandId) {
        try {
          return  branchRepository.findByBrand_id(brandId).stream()
                    .map(branch -> BaseDTO.builder()
                            .id(branch.getId())
                            .name(branch.getName())
                            .nameAr(branch.getNameAr())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }
}
