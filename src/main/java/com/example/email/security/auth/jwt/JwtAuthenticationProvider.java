package com.example.email.security.auth.jwt;


import com.example.email.security.auth.JwtAuthenticationToken;
import com.example.email.security.config.JwtSettings;
import com.example.email.security.model.UserContext;
import com.example.email.security.model.token.RawAccessJwtToken;
import com.goapptiv.entities.Email;
import com.goapptiv.services.base.EmailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtSettings jwtSettings;

    @Autowired
    EmailService emailService;

    /**
     * JwtAuthenticationProvider Constructor
     *
     * @param jwtSettings (required) Jwt Settings
     */
    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    /**
     * Authenticate the JwtToken
     *
     * @param authentication (required) current authentication
     * @return Authentication
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // initialize variables
        Long userId = 0L;

        // get raw token
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        // get claims from token
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());

        // get user
        String user_str = jwsClaims.getBody().get("user_id", String.class);
        if (!StringUtils.isEmpty(user_str))
            userId = Long.parseLong(user_str);

        // get permissions
        List<String> permissions = jwsClaims.getBody().get("email_permission", List.class);

        // converting permissions to authorities
        List<GrantedAuthority> authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // prepare user context
        UserContext context = UserContext.create(userId, authorities);

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
