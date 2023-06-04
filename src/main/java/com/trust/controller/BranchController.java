package com.trust.controller;

import com.trust.dto.ResponsePayload;
import com.trust.dto.branch.BranchDTO;
import com.trust.dto.branch.BranchFilterDTO;
import com.trust.dto.branch.CreateBranchDTO;
import com.trust.service.IBranchService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("api/v1/branch")
@RestController
public class BranchController {
    private final IBranchService branchService;

    @PostMapping("create")
    @ApiOperation("Create a new branch")
    public ResponseEntity<ResponsePayload> create (@RequestBody @Valid CreateBranchDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Branch is successfully created!",
                        "data", branchService.create(dto)))
                .build()
        );
    }

    @PostMapping("update")
    @ApiOperation("update an existing new branch")
    public ResponseEntity<ResponsePayload> update (@RequestBody @Valid BranchDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Branch is successfully updated!",
                        "data", branchService.update(dto)))
                .build()
        );
    }

    @PostMapping("filter")
    @ApiOperation("Return a page of branches by some filters")
    public ResponseEntity<ResponsePayload> filter (@RequestBody @Valid BranchFilterDTO dto) {
        Page<BranchDTO> response = branchService.filter(dto);
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Branch is successfully updated!",
                        "data", response.getContent()))
                        .pageable(response.getPageable())
                        .totalElements(response.getTotalElements())
                        .totalPages(response.getTotalPages())
                .build()
        );
    }

    @GetMapping("by-id")
    @ApiOperation("Get an existing branch by id")
    public ResponseEntity<ResponsePayload> findById (@RequestParam String id) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Branch is successfully fetched!",
                        "data", branchService.findById(id)))
                .build()
        );
    }

    @GetMapping("abstracted-by-brand")
    @ApiOperation("Get list of branch's as [id,name] by brand id")
    public ResponseEntity<ResponsePayload> abstractedByBrand (@RequestParam String brandId) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Branch list is successfully fetched!",
                        "data", branchService.abstractedByBrand(brandId)))
                .build()
        );
    }

    @DeleteMapping("delete")
    @ApiOperation("delete an existing branch")
    public ResponseEntity<ResponsePayload> delete (@RequestParam String id) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Branch is successfully deleted!",
                        "data", branchService.delete(id)))
                .build()
        );
    }

    }
