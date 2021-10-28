package org.guzman.despachalo.external.awss3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter @Setter
@ConfigurationProperties("despachalo.storage.aws")
public class AwsStorageConfig {
    private String region;
    private Credentials credentials;
    private Bucket bucket;
    private Integer minutesPresigned;

    @Getter @Setter
    public static class Credentials {
        private String accessKey;
        private String secretKey;
    }

    @Getter @Setter
    public static class Bucket {
        private String name;
    }
}
