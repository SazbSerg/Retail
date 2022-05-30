package com.retail.Retail.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_DISABLED;

    @Override
    public String getAuthority() {
        return name();
    }
}
