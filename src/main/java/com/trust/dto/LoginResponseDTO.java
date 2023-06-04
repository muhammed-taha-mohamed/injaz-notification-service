package com.trust.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResponseDTO {
    private String token;
    private String id;
    private String name;
    private String image;
    private String email;

}
