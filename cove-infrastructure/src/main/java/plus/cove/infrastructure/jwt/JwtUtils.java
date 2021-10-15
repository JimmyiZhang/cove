package plus.cove.infrastructure.jwt;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.infrastructure.exception.JwtError;

import javax.validation.constraints.NotNull;


/**
 * jwt工具类
 *
 * @author jimmy.zhang
 * @date 2019-04-03
 */
@Component
public class JwtUtils {
    private final Logger log = LoggerFactory.getLogger(JwtUtils.class);
    private static final String DEFAULT_ACTOR = "USER";
    private static final String DEFAULT_EXTRA = "EXTRA";

    @Autowired
    UniteJwtConfig config;

    /**
     * 生成Token
     * 按照规则生成token
     *
     * @param id 编码
     * @return token
     * @author jimmy.zhang
     * @date 2019-04-03
     */
    public JwtResult create(@NotNull String id) {
        return this.create(id, DEFAULT_ACTOR, DEFAULT_EXTRA);
    }

    /**
     * 生成Token
     * 按照规则生成token
     *
     * @param id 编码
     * @return token
     * @author jimmy.zhang
     * @date 2019-04-03
     */
    public JwtResult create(@NotNull String id, String actor, String extra) {
        String tokenSecret = config.getTokenSecret();
        if (StrUtil.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtError.INVALID_SECRET);
        }

        String jwtId = RandomUtil.randomString(10);
        Long now = DateUtil.currentSeconds();
        Long exp = DateUtil.currentSeconds() + config.getTokenExpired().toSeconds();

        JwtResult result;
        try {
            JWTSigner signer = JWTSignerUtil.hs512(tokenSecret.getBytes());
            String token = JWT.create()
                    .setPayload("sub", config.getTokenSubject())
                    .setPayload("iss", config.getTokenIssue())
                    .setPayload("jti", jwtId)
                    .setPayload("iat", now.toString())
                    .setPayload("exp", exp.toString())
                    .setPayload(config.getTokenClaim(), id)
                    .setPayload(config.getTokenActor(), actor)
                    .setPayload(config.getTokenExtra(), extra)
                    .setSigner(signer)
                    .sign();
            result = new JwtResult(token, (int)config.getTokenExpired().toMinutes());
        } catch (JWTException ex) {
            throw new BusinessException(JwtError.CREATION_EXCEPTION, ex);
        }
        return result;
    }

    /**
     * token解密
     *
     * @param token token串
     * @return id
     * @author jimmy.zhang
     * @date 2019-04-03
     */
    public JwtClaim decode(@NotNull String token) {
        String tokenSecret = config.getTokenSecret();
        if (StrUtil.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtError.INVALID_SECRET);
        }

        JwtClaim claim;
        try {
            JWTSigner signer = JWTSignerUtil.hs512(tokenSecret.getBytes());
            JWT jwt = JWT.of(token)
                    .setSigner(signer);

            // 验证算法与过期时间
            boolean verify = jwt.validate(5);
            if (!verify) {
                claim = JwtClaim.failure(JwtError.VERIFICATION_EXCEPTION.getMessage());
                return claim;
            }

            String id = jwt.getPayload(config.getTokenClaim()).toString();
            String extra = jwt.getPayload(config.getTokenExtra()).toString();
            String actor = jwt.getPayload(config.getTokenActor()).toString();
            if (actor == null || actor == "") {
                actor = config.getTokenActor();
            }

            claim = JwtClaim.success(id, actor, extra);
        } catch (JWTException ex) {
            log.warn("jwt解析失败：{}", token);
            claim = JwtClaim.failure(JwtError.DECODE_EXCEPTION.getMessage());
        }
        return claim;
    }

    /**
     * 校验
     *
     * @param token JWT 字符串
     * @return true 验证通过
     * @author jimmy.zhang
     * @date 2019-04-03
     */
    public boolean verify(String token) {
        String tokenSecret = config.getTokenSecret();
        if (StrUtil.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtError.INVALID_SECRET);
        }

        boolean verify;
        try {
            JWT jwt = JWT.of(token);
            // 验证算法与过期时间
            verify = jwt.setKey(tokenSecret.getBytes()).validate(5);
        } catch (JWTException exception) {
            verify = false;
        }
        return verify;
    }
}
