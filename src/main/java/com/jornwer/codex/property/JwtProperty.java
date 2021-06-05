package com.jornwer.codex.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

    /**
     * Secret key for JWT
     */
    private String secret;

    /**
     * Header name for storing JWT
     */
    private String header;

    /**
     * Number of milliseconds before JWT expires
     */
    private long expiration;
}
