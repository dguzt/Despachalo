package org.guzman.despachalo.web.config.security.controllers;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.web.config.security.model.WebUser;
import org.guzman.despachalo.web.config.security.model.WebUserDetails;
import org.guzman.despachalo.web.config.security.model.WebUserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class WhoIAmController {
    private final WebUserMapper webUserMapper;

    @GetMapping("/auth/me")
    @ResponseStatus(HttpStatus.OK)
    public WebUser whoIAm(@AuthenticationPrincipal WebUserDetails userDetails) {
        return webUserMapper.toWebUser(userDetails.getUser());
    }
}
