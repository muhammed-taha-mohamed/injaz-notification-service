package com.trust.dto;

import com.trust.enummeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GenerateTokenDto {
    private String username;
    private String userId;
    private String email;
    private Role role;
    private String userKey;
}
