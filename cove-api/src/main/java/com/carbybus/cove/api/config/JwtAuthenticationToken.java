package com.carbybus.cove.api.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * jwt认证token
 *
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
    public boolean equals(Object obj) {
        if (!(obj instanceof JwtAuthenticationToken)) {
            return false;
        } else {
            JwtAuthenticationToken ob = (JwtAuthenticationToken) obj;
            return this.getName().equals(ob.getName()) && this.isAuthenticated() == ob.isAuthenticated();
        }
    }

    @Override
    public int hashCode() {
        int code = 31;
        code ^= this.isAuthenticated() ? 0 : 1;
        code ^= this.getName().hashCode();
        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.getName()).append("; ");
        sb.append("Authenticated: ").append(this.isAuthenticated()).append("; ");

        return sb.toString();
    }
}
