package com.example.email.security.config;

import com.example.email.security.RestAuthenticationEntryPoint;
import com.example.email.security.auth.jwt.JwtAuthenticationProvider;
import com.example.email.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.goapptiv.entities.Email;
import com.goapptiv.entities.enums.Permission;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // authorization token name
    public static final String AUTHENTICATION_HEADER_NAME = "GA_Authorization";

    // all api routes
    public static final String API_ROOT_URL = "/v1/**";

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtSettings jwtSettings;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable csrf for jwt tokens

                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint) // Handle Authentication Exception

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Make all requests stateless

                .and()
                .authorizeRequests()
                .antMatchers(API_ROOT_URL).authenticated() // Protect all API End-points

                .and()
                .addFilterBefore(
                        buildJwtTokenAuthenticationProcessingFilter(API_ROOT_URL),
                        UsernamePasswordAuthenticationFilter.class
                );

        List<String> permissions = new ArrayList<>();
        for (Permission s : Permission.values()) {
            permissions.add(s.getPermission());
        }

        String compactJws = Jwts.builder()
                .setSubject("1")
                .signWith(SignatureAlgorithm.HS512, jwtSettings.getTokenSigningKey())
                .claim("email_permission", permissions)
                .claim("user_id","1")
                .compact();
        System.out.println(compactJws);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    /**
     * Prepare Jwt token authentication filter
     *
     * @param pattern (required) url pattern to apply filter on
     * @return JwtTokenAuthenticationProcessingFilter
     * @throws Exception
     */
    private JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(String pattern) throws Exception {
        // prepare route matcher
        AntPathRequestMatcher processingMatcher = new AntPathRequestMatcher(pattern);

        // prepare jwt authentication processing
        JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(failureHandler, processingMatcher);

        // set authentication manager for filter
        filter.setAuthenticationManager(this.authenticationManager);

        // return filter
        return filter;
    }
}