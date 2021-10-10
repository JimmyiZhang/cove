package plus.cove.infrastructure.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * jwt认证token
 *
 * @author jimmy.zhang
 * @modify 2020-08-28增加扩展字段extra
 * @date 2019-05-15
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private String claim;
    private String extra;
    private String actor;

    public JwtAuthenticationToken(String claim, String actor, String extra) {
        super(AuthorityUtils.createAuthorityList(actor));
        this.claim = claim;
        this.actor = actor;
        this.extra = extra;

        setAuthenticated(true);
    }

    public JwtAuthenticationToken(String claim, String authority) {
        this(claim, authority, null);
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

    public String getExtra() {
        return this.extra;
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
        if (this.extra != null && this.extra != "") {
            code ^= this.extra.hashCode();
        }
        if (this.actor != null && this.actor != "") {
            code ^= this.actor.hashCode();
        }
        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.getName());
        sb.append(", Authenticated: ").append(this.isAuthenticated());
        if (this.extra != null && this.extra != "") {
            sb.append(", Extra: ").append(this.extra);
        }
        if (this.actor != null && this.actor != null) {
            sb.append(", Actor: ").append(this.actor);
        }
        return sb.toString();
    }
}
