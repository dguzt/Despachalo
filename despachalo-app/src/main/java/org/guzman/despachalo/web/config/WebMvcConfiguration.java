package org.guzman.despachalo.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.URL;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration {
    private final SecurityVars securityVars;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        var domains = securityVars.getAllowedDomains()
                .stream()
                .map(URL::toString)
                .collect(Collectors.joining(","));

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(domains);
            }
        };
    }
}
