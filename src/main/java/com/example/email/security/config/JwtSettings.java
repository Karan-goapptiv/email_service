package com.example.email.security.config;

import com.example.email.security.model.token.JwtToken;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "goapptiv.security.jwt")
public class JwtSettings {

    /**
     * Key is used to sign {@link JwtToken}.
     */
    private String tokenSigningKey;

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }
}
