package com.lullaby.study.hexagonalkata.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final List<String> NOT_FILTERED_URL = List.of("/auth/sign-up", "/auth/sign-in", "/health-check", "/error");

    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = jwtProvider.fetchBearerToken(request);

        if (!jwtProvider.validate(bearerToken)) {
            throw new AuthenticateFailException();
        }

        SecurityContextHolder.getContext().setAuthentication(jwtProvider.authenticate(bearerToken));

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return this.NOT_FILTERED_URL.stream().anyMatch(it -> it.equals(request.getRequestURI().toString()));
    }
}
