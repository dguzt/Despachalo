package org.guzman.despachalo.adapter.web.config.security.controllers;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.web.config.security.services.JwtService;
import org.guzman.despachalo.adapter.web.config.security.services.WebUserDetailsService;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
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
public class AuthenticationController {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final WebUserDetailsService webUserDetailsService;

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException ex) {
            logger.info("Trying to login with email: {}", authenticationRequest.getEmail());
            throw new WrongCredentialsException();
        }

        final var userDetails = webUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final var token = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(token);
    }
}
