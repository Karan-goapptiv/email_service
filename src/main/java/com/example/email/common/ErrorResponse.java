package com.example.email.common;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {

    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Timestamp
    private final Date timestamp;

    /**
     * ErrorResponse Constructor
     *
     * @param message (required) error message
     * @param status  (required) status
     */
    protected ErrorResponse(final String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Date();
    }

    public static ErrorResponse of(final String message, HttpStatus status) {
        return new ErrorResponse(message, status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
