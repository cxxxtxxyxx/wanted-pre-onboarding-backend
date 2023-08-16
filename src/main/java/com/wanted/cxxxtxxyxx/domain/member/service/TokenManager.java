package com.wanted.cxxxtxxyxx.domain.member.service;

import com.wanted.cxxxtxxyxx.domain.member.code.TokenErrorCode;
import com.wanted.cxxxtxxyxx.domain.member.exception.InvalidJwtTokenException;
import com.wanted.cxxxtxxyxx.domain.member.vo.TokenPayload;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class TokenManager {
    private final SecretKey accessSecretKey;
    private final int accessTokenExpires;

    public TokenManager(
            @Value("${jwt.access-secret}") String accessSecretKey,
            @Value("${jwt.access-token-expires}") String accessTokenExpires
    ) {
        this.accessSecretKey = Keys.hmacShaKeyFor(accessSecretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpires = Integer.parseInt(accessTokenExpires);
    }

    public Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(accessSecretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new InvalidJwtTokenException(TokenErrorCode.EXPIRED_ACCESS_TOKEN);
        }
    }

    public boolean validateAccessToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(accessSecretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.info("Invalid JWT Signature", e);
            throw new InvalidJwtTokenException(TokenErrorCode.INVALID_SIGNATURE_IN_ACCESS_TOKEN);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            throw new InvalidJwtTokenException(TokenErrorCode.INVALID_ACCESS_TOKEN);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            throw new InvalidJwtTokenException(TokenErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new InvalidJwtTokenException(TokenErrorCode.WRONG_TYPE_ACCESS_TOKEN);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            throw new InvalidJwtTokenException(TokenErrorCode.UNKNOWN_ACCESS_TOKEN_ERROR);
        }
    }


    public Long getIdFromToken(String token) {
        Claims parsedClaims = parseClaims(token);
        return parsedClaims.get("id", Long.class);
    }

    public String generateAccessToken(TokenPayload tokenPayload) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenExpires);
        return Jwts.builder()
                .claim("id", tokenPayload.getId())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setHeaderParam("type", "jwt")
                .signWith(accessSecretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
