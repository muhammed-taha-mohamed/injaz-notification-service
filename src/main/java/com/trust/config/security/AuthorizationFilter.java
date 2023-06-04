/*
package com.trust.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trust.dto.ResponsePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        try {
            if (isPublicRequest(request)) {
                chain.doFilter(request, response);
                return;
            }

            String jwtToken = extractJwtFromRequest(request);

            if (jwtToken == null) {
                throwError(response, SC_UNAUTHORIZED, "Access denied, Unauthorized request!!");
                return;
            } else if (!jwtTokenProvider.validateToken(jwtToken)) {
                throwError(response, SC_UNAUTHORIZED, "Access denied, Token is expired!!");
                return;
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throwError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    private boolean isPublicRequest(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return publicRequests.stream()
                .anyMatch(uri -> requestURI.contains(uri));
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static final List<String> publicRequests = List.of(

            "/swagger-ui.html",
            "/webjars/springfox-swagger-ui",
            "/swagger-resources",
            "/v2/api-docs",

            "/api/v1/customer/sign-up",
            "/api/v1/customer/send-otp",
            "/api/v1/customer/verify-otp",
            "/trust/error",
            "refresh-token",
            "/api/v1/brand/sign-up"
    );

    private void throwError(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        ResponsePayload payload = ResponsePayload.builder()
                .error(Map.of("errorMessage", message))
                .build();
        objectMapper.writeValue(response.getWriter(), payload);
    }
}
*/
