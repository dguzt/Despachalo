package org.guzman.despachalo.adapter.web.config.security.controllers;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.web.config.security.services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String PREFIX = "Bearer ";

    private final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Start filtering internal JWT");
        final var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith(PREFIX)) {
            logger.info("Request does not have Authorization Header or start with '{}'", PREFIX);
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = authorizationHeader.substring(PREFIX.length());
        var username = jwtService.extractUsername(jwt);
        logger.info("Authenticated as {}", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("Request user details of {}", username);
            var userDetails = userDetailsService.loadUserByUsername(username);
            var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
