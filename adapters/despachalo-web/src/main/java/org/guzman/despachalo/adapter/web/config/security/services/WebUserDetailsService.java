package org.guzman.despachalo.adapter.web.config.security.services;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.web.config.security.model.WebUserDetails;
import org.guzman.despachalo.adapter.web.config.security.model.WebUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class WebUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(WebUserDetailsService.class);
    private final WebUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = repository.findByUserEmail(email)
                .orElseThrow(() -> {
                    logger.info("User not found with email: {}", email);
                    throw new UsernameNotFoundException("User not found with email: " + email);
                });

        return new WebUserDetails(email, user);
    }
}
