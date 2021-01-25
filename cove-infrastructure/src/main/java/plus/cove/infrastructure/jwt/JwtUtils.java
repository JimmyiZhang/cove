package plus.cove.infrastructure.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.infrastructure.exception.JwtError;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

/**
 * jwt工具类
 *
 * @author jimmy.zhang
 * @date 2019-04-03
 */
@Slf4j
@Component
public class JwtUtils {
    private static final String CLAIM_EXTRA = "extra";

    @Autowired
    private UniteJwtConfig config;

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
        String tokenSecret = config.getTokenSecret();
        if (StringUtils.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtError.INVALID_SECRET);
        }

        Date now = Date.from(Instant.now());
        int expired = (int) config.getTokenExpired().toMinutes();
        Date exp = Date.from(Instant.now().plus(expired, ChronoUnit.MINUTES));
        String jwtId = UUID.randomUUID().toString();

        JwtResult result;
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecret);
            String token = JWT.create()
                    .withIssuer(config.getTokenIssue())
                    .withIssuedAt(now)
                    .withSubject(config.getTokenSubject())
                    .withExpiresAt(exp)
                    .withJWTId(jwtId)
                    .withClaim(config.getTokenClaim(), id)
                    .sign(algorithm);

            result = new JwtResult(token, expired);
        } catch (JWTCreationException ex) {
            throw new BusinessException(JwtError.CREATION_EXCEPTION, ex);
        }
        return result;
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
    public JwtResult create(@NotNull String id, String extra) {
        String tokenSecret = config.getTokenSecret();
        if (StringUtils.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtError.INVALID_SECRET);
        }

        Date now = Date.from(Instant.now());
        int expired = (int) config.getTokenExpired().toMinutes();
        Date exp = Date.from(Instant.now().plus(expired, ChronoUnit.MINUTES));
        String jwtId = UUID.randomUUID().toString();

        JwtResult result;
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecret);
            String token = JWT.create()
                    .withIssuer(config.getTokenIssue())
                    .withIssuedAt(now)
                    .withSubject(config.getTokenSubject())
                    .withExpiresAt(exp)
                    .withJWTId(jwtId)
                    .withClaim(config.getTokenClaim(), id)
                    .withClaim(CLAIM_EXTRA, extra)
                    .sign(algorithm);

            result = new JwtResult(token, expired);
        } catch (JWTCreationException ex) {
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
        if (StringUtils.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtError.INVALID_SECRET);
        }

        JwtClaim claim;
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(config.getTokenIssue())
                    .acceptExpiresAt(5)
                    .build();
            verifier.verify(token);

            DecodedJWT code = JWT.decode(token);
            String id = code.getClaim(config.getTokenClaim()).asString();
            String extra = code.getClaim(CLAIM_EXTRA).asString();
            claim = new JwtClaim(true, id, extra);
        } catch (JWTDecodeException ex) {
            throw new BusinessException(JwtError.DECODE_EXCEPTION, ex);
        } catch (JWTVerificationException ex) {
            log.info("jwt认证过期：{}", token);
            claim = new JwtClaim(false, JwtError.VERIFICATION_EXCEPTION.getMessage(), null);
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
        if (StringUtils.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtError.INVALID_SECRET);
        }

        boolean verify;
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(config.getTokenIssue())
                    .acceptExpiresAt(5)
                    .build();

            verifier.verify(token);
            verify = true;
        } catch (JWTVerificationException exception) {
            verify = false;
        }
        return verify;
    }

    /**
     * 刷新
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-15
     */
    public JwtResult refresh(String token) {
        String tokenSecret = config.getTokenSecret();
        if (StringUtils.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtError.INVALID_SECRET);
        }

        JwtResult result = null;
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(config.getTokenIssue())
                    .acceptExpiresAt(5)
                    .build();

            verifier.verify(token);
            DecodedJWT code = JWT.decode(token);
            String id = code.getClaim(config.getTokenClaim()).asString();

            // 重新生成
            Date now = Date.from(Instant.now());
            int expired = (int)config.getTokenExpired().toMinutes() * 2;
            Date exp = Date.from(Instant.now().plus(expired, ChronoUnit.MINUTES));
            String jwtId = UUID.randomUUID().toString();

            String reToken = JWT.create()
                    .withIssuer(config.getTokenIssue())
                    .withIssuedAt(now)
                    .withSubject(config.getTokenSubject())
                    .withExpiresAt(exp)
                    .withJWTId(jwtId)
                    .withClaim(config.getTokenClaim(), id)
                    .sign(algorithm);

            result = new JwtResult(reToken, expired);
        } catch (JWTDecodeException ex) {
            throw new BusinessException(JwtError.DECODE_EXCEPTION, ex);
        } catch (JWTVerificationException ex) {
            throw new BusinessException(JwtError.VERIFICATION_EXCEPTION, ex);
        }

        return result;
    }
}
