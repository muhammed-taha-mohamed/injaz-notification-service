package com.trust.controller;

import com.trust.dto.ResponsePayload;
import com.trust.dto.customer.CreateCustomerDTO;
import com.trust.dto.customer.CustomerDTO;
import com.trust.dto.customer.CustomerLoginDTO;
import com.trust.dto.VerifyLoginOtpDTO;
import com.trust.service.ICustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {
    private final ICustomerService customerService;

    @PostMapping("sign-up")
    @ApiOperation("customer sign up api")
    public ResponseEntity<ResponsePayload> signUp (@RequestBody @Valid CreateCustomerDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Customer is successfully created!",
                        "data", customerService.create(dto)))
                .build()
        );
    }

    @PostMapping("update")
    @ApiOperation("Update customer profile")
    public ResponseEntity<ResponsePayload> update (@RequestBody @Valid CustomerDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Customer is successfully updated!",
                        "data", customerService.update(dto)))
                .build()
        );
    }

    @PostMapping("send-otp")
    @ApiOperation("customer login - step one -> send otp")
    public ResponseEntity<ResponsePayload> sendOtp (@RequestBody CustomerLoginDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","Otp is successfully sent!",
                        "data", customerService.sendOtp(dto)))
                .build()
        );
    }

    @PostMapping("verify-otp")
    @ApiOperation("customer login - step two -> verify otp")
    public ResponseEntity<ResponsePayload> verifyOtp (@RequestBody VerifyLoginOtpDTO dto) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","login successfully processed!",
                        "data", customerService.verifyOtp(dto)))
                .build()
        );
    }


    @GetMapping("get-details")
    @ApiOperation("get customer details by id")
    public ResponseEntity<ResponsePayload> getCustomerDetails (@RequestParam String id) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","customer details successfully fetched!",
                        "data", customerService.findById(id)))
                .build()
        );
    }

    @DeleteMapping("delete")
    @ApiOperation("delete customer details by id")
    public ResponseEntity<ResponsePayload> delete (@RequestParam String id) {
        return  ResponseEntity.ok(ResponsePayload.builder()
                .date(LocalDateTime.now())
                .content(Map.of(
                        "message","customer account is successfully deleted!",
                        "data", customerService.delete(id)))
                .build()
        );
    }


    }
