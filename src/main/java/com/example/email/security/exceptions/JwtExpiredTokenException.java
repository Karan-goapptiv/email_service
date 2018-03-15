package com.example.email.security.exceptions;

import com.example.email.security.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;


public class JwtExpiredTokenException extends AuthenticationException {

    private static final long serialVersionUID = -5959543783324224864L;

    // json token
    private JwtToken token;

    /**
     * JwtExpiredTokenException Constructor
     *
     * @param msg (required) error message
     */
    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    /**
     * JwtExpiredTokenException Constructor
     *
     * @param token (required) json token
     * @param msg (required) error message
     * @param t Throwable
     */
    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
