package com.carbybus.cove.api.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * jwt认证token
 * @author jimmy.zhang
 * @date 2019-05-15
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private String claim;

    public JwtAuthenticationToken(String claim) {
        super(null);
        this.claim = claim;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public String getName() {
        return this.claim;
    }

    @Override
    public boolean isAuthenticated() {
        return super.isAuthenticated();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        super.setAuthenticated(authenticated);
    }
}
