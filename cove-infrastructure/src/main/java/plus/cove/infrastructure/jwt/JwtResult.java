package plus.cove.infrastructure.jwt;

import lombok.Getter;

/**
 * jwt输入结果
 * 包括token和过期时间
 *
 * @author jimmy.zhang
 * @date 2019-05-15
 */
public class JwtResult {
    /**
     * token值
     */
    @Getter
    private String token;

    /**
     * 过期时间，单位分
     */
    @Getter
    private Integer expire;

    public JwtResult(String token, Integer expire) {
        this.token = token;
        this.expire = expire;
    }

    @Override
    public String toString() {
        return String.format("token: %s, expire: %sm", this.token, this.expire);
    }
}
