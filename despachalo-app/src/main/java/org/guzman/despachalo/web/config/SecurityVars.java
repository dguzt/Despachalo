package org.guzman.despachalo.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.ArrayList;

@Configuration
@Getter @Setter
@ConfigurationProperties(prefix = "despachalo.security")
public class SecurityVars {
    private ArrayList<URL> allowedDomains;
    private String jwtKey;
}
