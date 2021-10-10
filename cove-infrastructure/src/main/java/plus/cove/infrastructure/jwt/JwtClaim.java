package plus.cove.infrastructure.jwt;

import lombok.Getter;

/**
 * jwt输出结果
 * 包括是否成功和身份
 *
 * @author jimmy.zhang
 * @date 2019-05-15
 */
public class JwtClaim {
    /**
     * 是否成功
     */
    @Getter
    private Boolean success;
    @Getter
    private String message;

    /**
     * 声明
     */
    @Getter
    private String claim;
    @Getter
    private String actor;
    @Getter
    private String extra;

    public static JwtClaim success(String claim, String actor, String extra) {
        JwtClaim jwt = new JwtClaim();
        jwt.success = true;
        jwt.claim = claim;
        jwt.actor = actor;
        jwt.extra = extra;

        return jwt;
    }

    public static JwtClaim failure(String message) {
        JwtClaim jwt = new JwtClaim();
        jwt.success = false;
        jwt.message = message;
        return jwt;
    }
}
