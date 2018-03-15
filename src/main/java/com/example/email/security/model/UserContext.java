package com.example.email.security.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserContext {

    private final Long userId;

    private final List<GrantedAuthority> authorities;

    /**
     * User context constructor
     *
     * @param userId        required
     * @param authorities   list of authorities
     */
    private UserContext(Long userId, List<GrantedAuthority> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

    /**
     * Create new UserContext for email id and authorities
     *
     * @param userId        required
     * @param authorities   (required) list of authorities user has
     * @return UserContext
     */
    public static UserContext create(Long userId, List<GrantedAuthority> authorities) {

        return new UserContext(userId,authorities);
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getUserId() { return userId; }
}
