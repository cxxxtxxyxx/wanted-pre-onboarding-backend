package com.wanted.cxxxtxxyxx.domain.member.service;

import com.wanted.cxxxtxxyxx.domain.member.vo.TokenPayload;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    private static final String ACCESS_TOKEN_Header = "Authorization";
    private final SecretKey accessSecretKey;
    private final int accessTokenExpires;

    public TokenManager(
            @Value("${jwt.access-secret}") String accessSecretKey,
            @Value("${jwt.access-token-expires}") String accessTokenExpires
    ) {
        this.accessSecretKey = Keys.hmacShaKeyFor(accessSecretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpires = Integer.parseInt(accessTokenExpires);
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
