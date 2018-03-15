package com.example.email.controller.base;

import com.example.email.security.auth.JwtAuthenticationToken;
import com.example.email.security.model.UserContext;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    /**
     * Result Map
     */
    private Map<String, Object> result = new HashMap<>();

    protected UserContext getUser(Principal principal) {
        return (UserContext) ((JwtAuthenticationToken) principal).getPrincipal();
    }

    /**
     * Add data to result array
     */
    private void addResult(String key, Object value) {
        this.result.put(key, value);
    }

    /**
     * Return Success response
     */
    protected Map<String, Object> done(String key, Object value) {
        this.addResult(key, value);
        return this.result;
    }
}
