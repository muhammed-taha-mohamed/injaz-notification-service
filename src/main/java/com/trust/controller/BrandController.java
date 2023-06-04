package com.trust.controller;

import com.trust.dto.LoginDTO;
import com.trust.dto.ResponsePayload;
import com.trust.dto.brand.CreateBrandDTO;
import com.trust.service.IBrandService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("api/v1/brand")
@RestController
public class BrandController {
    private final IBrandService brandService;

    @PostMapping("sign-up")
    @ApiOperation("Brand sign up api")
    public ResponseEntity<ResponsePayload> signUp (@RequestBody @Valid CreateBrandDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Brand is successfully created!",
                        "data", brandService.create(dto)))
                .build()
        );
    }

    @PostMapping("login")
    @ApiOperation("Brand sign in api")
    public ResponseEntity<ResponsePayload> login (@RequestBody @Valid LoginDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","User is logged in created!",
                        "data", brandService.login(dto)))
                .build()
        );
    }


    @PostMapping("create-user")
    @ApiOperation("Create a user for specific brand")
    public ResponseEntity<ResponsePayload> createUser (@RequestBody @Valid LoginDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","User is created created!",
                        "data", brandService.login(dto)))
                .build()
        );
    }

    }
