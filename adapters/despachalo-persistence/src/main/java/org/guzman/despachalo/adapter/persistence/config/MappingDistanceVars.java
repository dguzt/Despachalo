package org.guzman.despachalo.adapter.persistence.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "despachalo.mapping")
public class MappingDistanceVars {
    private String apiKey;
}
