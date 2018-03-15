package com.example.email.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

public class EmailNotFoundException extends AuthenticationServiceException {

    private static final long serialVersionUID = 3705043083010304496L;

    public EmailNotFoundException(String msg) {
        super(msg);
    }
}
