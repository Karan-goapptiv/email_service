package com.example.email.security.auth.handler;

import com.example.email.common.ErrorResponse;
import com.example.email.security.exceptions.JwtExpiredTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class GaAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    /**
     * AjaxAwareAuthenticationFailureHandler Constructor
     *
     * @param mapper
     */
    @Autowired
    public GaAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * On authentication failure write to ObjectMapper
     *
     * @param request  (required) HttpServletRequest object
     * @param response (required) HttpServletResponse object
     * @param e        (required) AuthenticationException object
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        // set unauthorized status & application/json value
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // on token expiry set into mapper
        if (e instanceof JwtExpiredTokenException) {
            mapper.writeValue(
                    response.getWriter(),
                    ErrorResponse.of("Token has expired", HttpStatus.UNAUTHORIZED)
            );
        }

        // add authentication failed to mapper
        mapper.writeValue(
                response.getWriter(),
                ErrorResponse.of("Authentication failed", HttpStatus.UNAUTHORIZED)
        );
    }
}
