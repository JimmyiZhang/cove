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

    public JwtAuthenticationToken(String claim, String authority, String extra) {
        super(AuthorityUtils.createAuthorityList("ROLE_" + authority));
        this.claim = claim;
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
            code ^= this.getExtra().hashCode();
        }
        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.getName()).append("; ");
        sb.append("Authenticated: ").append(this.isAuthenticated()).append("; ");
        if (this.extra != null && this.extra != "") {
            sb.append("Extra: ").append(this.extra).append("; ");
        }
        return sb.toString();
    }
}
