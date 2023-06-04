package com.trust.config.security;

import com.trust.dto.GenerateTokenDto;
import com.trust.enummeration.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Generates a JWT (JSON Web Token) with the provided user information.
     * @return The generated JWT as a string.
     */
    public String generateToken(GenerateTokenDto dto) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .claim("id", dto.getUserId())
                .claim("email",dto.getEmail())
                .claim("role", dto.getRole())
                .claim("userKey", dto.getUserKey())
                .setSubject(dto.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }



    /**
     * Refreshes a JWT by parsing the provided token, retrieving the claims,
     * and generating a new token with the existing user information.
     * @param token The JWT to refresh.
     * @return The refreshed JWT as a string.
     */
    public String refreshToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // If the token is expired, retrieve the claims without validation
            claims = e.getClaims();
        }

        String username = claims.getSubject();
        String userId = claims.get("userId", String.class);
        String email = claims.get("email", String.class);
        String role = claims.get("role", String.class);
        String userKey = claims.get("userKey", String.class);

        return generateToken(GenerateTokenDto.builder()
                .userKey(userKey)
                .username(username)
                .email(email)
                .role(Role.valueOf(role))
                .userId(userId)
                .build());
    }



    /**
     * Retrieves a specific claim from the provided JWT.
     * @param token The JWT to retrieve the claim from.
     * @param key   The key of the claim to retrieve.
     * @return The value of the claim as a string.
     */
    private String getClaimFromToken(String token, String key) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.get(key, String.class);
    }

    /**
     * Retrieves the username from the provided JWT.
     * @param token The JWT to retrieve the username from.
     * @return The username as a string.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims.SUBJECT);
    }

    /**
     * Retrieves the user ID from the provided JWT.
     * @param token The JWT to retrieve the user ID from.
     * @return The user ID as a string.
     */
    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token, "userId");
    }

    /**
     * Retrieves the email from the provided JWT.
     * @param token The JWT to retrieve the email from.
     * @return The email as a string.
     */
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, "email");
    }

    /**
     * Retrieves the role from the provided JWT.
     * @param token The JWT to retrieve the role from.
     * @return The role as a string.
     */
    public String getRoleFromToken(String token) {
        return getClaimFromToken(token, "role");
    }

    public boolean validateToken (String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
