package com.trust.controller;

import com.trust.config.security.JwtTokenProvider;
import com.trust.dto.ResponsePayload;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("refresh-token")
    @ApiOperation("generate a new token from the expired token")
    public ResponseEntity<ResponsePayload> refreshToken (@RequestParam String token) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "data", jwtTokenProvider.refreshToken(token)))
                .build()
        );
    }

    }
