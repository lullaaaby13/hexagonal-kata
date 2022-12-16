package com.lullaby.study.hexagonalkata.security;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Date;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.substringAfter;

@Component
public class JwtProvider {

    private Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private String TOKEN_PREFIX = "Bearer ";
    private String JWT_SECRET = "my_secret";
    private Integer ACCESS_TOKEN_EXPIRATION_MS = 1000 * 60 * 60; // 3600초
    private Integer REFRESH_TOKEN_EXPIRATION_MS = 1000 * 60 * 60 * 24 * 7; // 7일

    private JwtParser jwtParser = Jwts.parser().setSigningKey(JWT_SECRET);

    public String fetchBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (Objects.isNull(authorization)) {
            throw new InvalidTokenException();
        }

        if (!authorization.startsWith(TOKEN_PREFIX)) {
            throw new InvalidTokenException();
        }

        return substringAfter(authorization, TOKEN_PREFIX);
    }

    public String fetchBearerToken(NativeWebRequest request) {
        String authorization = request.getHeader("Authorization");

        if (Objects.isNull(authorization)) {
            throw new InvalidTokenException();
        }

        if (!authorization.startsWith(TOKEN_PREFIX)) {
            throw new InvalidTokenException();
        }

        return substringAfter(authorization, TOKEN_PREFIX);
    }
    public String accessToken(Long userId)  {

        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + ACCESS_TOKEN_EXPIRATION_MS);

        Claims claims = Jwts.claims().setSubject(userId.toString());
        claims.put("roles", "USER");

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String refreshToken(Long userId) {

        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + REFRESH_TOKEN_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String parseSubject(String token) {
        return (String) this.jwtParser.parseClaimsJws(token).getBody().get("sub");
    }

    public Authentication authenticate(String token) {
        Jws<Claims> parseClaimsJws = this.jwtParser.parseClaimsJws(token);
        String userId = (String) parseClaimsJws.getBody().get("sub");
        String roles = (String) parseClaimsJws.getBody().get("roles");
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(Long.valueOf(userId), "ROLE_$roles");
        return new UsernamePasswordAuthenticationToken(authenticatedUser, "", authenticatedUser.getAuthorities());
    }

    // Jwt 토큰 유효성 검사
    public Boolean validate(String token) {
        try {
            this.jwtParser.parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
