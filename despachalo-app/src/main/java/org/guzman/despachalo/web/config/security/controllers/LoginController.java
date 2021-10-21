package org.guzman.despachalo.web.config.security.controllers;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.web.config.security.model.Credentials;
import org.guzman.despachalo.web.config.security.model.Token;
import org.guzman.despachalo.web.config.security.model.WebUserDetails;
import org.guzman.despachalo.web.config.security.services.JwtService;
import org.guzman.despachalo.web.config.security.services.WebUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final WebUserDetailsService webUserDetailsService;

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public Token login(@RequestBody Credentials credentials) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException ex) {
            logger.info("Trying to login with email: {}", credentials.getEmail());
            throw new WrongCredentialsException();
        }

        final var userDetails = (WebUserDetails) webUserDetailsService.loadUserByUsername(credentials.getEmail());
        final var token = jwtService.generateToken(userDetails);

        return new Token(token);
    }
}
