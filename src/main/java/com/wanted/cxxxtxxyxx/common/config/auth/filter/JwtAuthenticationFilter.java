package com.wanted.cxxxtxxyxx.common.config.auth.filter;

import com.wanted.cxxxtxxyxx.domain.member.code.TokenErrorCode;
import com.wanted.cxxxtxxyxx.domain.member.exception.InvalidJwtTokenException;
import com.wanted.cxxxtxxyxx.domain.member.service.TokenManager;
import com.wanted.cxxxtxxyxx.domain.member.vo.TokenPayload;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final TokenManager tokenManager;

    private static final String ACCESS_TOKEN_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = extractBearerToken(request);

        if (authorizationHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = parseToken(authorizationHeader);


        if (tokenManager.validateAccessToken(accessToken)) {
            setContextHolder(accessToken);
            filterChain.doFilter(request, response);
            return;
        }

        throw new InvalidJwtTokenException(TokenErrorCode.NOT_EXIST_TOKEN);

    }

    private String parseToken(String authorizationHeader) {
        return authorizationHeader.split("Bearer ")[1];
    }

    private String extractBearerToken(HttpServletRequest request) {
        return request.getHeader(ACCESS_TOKEN_HEADER);
    }

    private Authentication getAuthentication(String accessToken) {
        Claims claims = tokenManager.parseClaims(accessToken);

        UserDetails principal = userDetailsService.loadUserByUsername(claims.get(TokenPayload.ID_PAYLOAD).toString());

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    private void setContextHolder(String accessToken) {
        Authentication authentication = getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
