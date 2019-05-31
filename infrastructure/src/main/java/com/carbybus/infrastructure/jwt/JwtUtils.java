package com.carbybus.infrastructure.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.JwtTokenError;
import com.carbybus.infrastructure.utils.StringConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class JwtUtils {
    @Autowired
    private  UniteJwtConfig config;

    /**
     * 工具类使用私有构造器覆盖公共构造器，防止公共构造器被调用
     * Sonar Code smell Major squid:S1118
     */
    private JwtUtils() {
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
    public JwtResult create(String id) {
        String tokenSecret = config.getTokenSecret();
        if (StringUtils.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtTokenError.INVALID_SECRET);
        }

        Date now = Date.from(Instant.now());
        int expMinutes = config.getTokenExpired();
        Date exp = Date.from(Instant.now().plus(expMinutes, ChronoUnit.MINUTES));
        String jwtId = UUID.randomUUID().toString();

        JwtResult result = new JwtResult();
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
            result.setToken(token);
            result.setExpire(expMinutes);
        } catch (JWTCreationException ex) {
            throw new BusinessException(JwtTokenError.CREATION_EXCEPTION, ex);
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
    public String decode(String token) {
        String tokenSecret = config.getTokenSecret();
        if (StringUtils.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtTokenError.INVALID_SECRET);
        }

        String id = StringConstants.EMPTY;
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(config.getTokenIssue())
                    .acceptExpiresAt(5)
                    .build();
            verifier.verify(token);

            DecodedJWT code = JWT.decode(token);
            id = code.getClaim(config.getTokenClaim()).asString();
        } catch (JWTDecodeException ex) {
            throw new BusinessException(JwtTokenError.DECODE_EXCEPTION, ex);
        } catch (JWTVerificationException ex) {
            throw new BusinessException(JwtTokenError.VERIFICATION_EXCEPTION, ex);
        }
        return id;
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
            throw new BusinessException(JwtTokenError.INVALID_SECRET);
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
            throw new BusinessException(JwtTokenError.INVALID_SECRET);
        }

        JwtResult result = new JwtResult();
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
            int expMinutes = config.getTokenExpired() * 2;
            Date exp = Date.from(Instant.now().plus(expMinutes, ChronoUnit.MINUTES));
            String jwtId = UUID.randomUUID().toString();

            String reToken = JWT.create()
                    .withIssuer(config.getTokenIssue())
                    .withIssuedAt(now)
                    .withSubject(config.getTokenSubject())
                    .withExpiresAt(exp)
                    .withJWTId(jwtId)
                    .withClaim(config.getTokenClaim(), id)
                    .sign(algorithm);

            result.setToken(reToken);
            result.setExpire(expMinutes);
        } catch (JWTDecodeException ex) {
            throw new BusinessException(JwtTokenError.DECODE_EXCEPTION, ex);
        } catch (JWTVerificationException ex) {
            throw new BusinessException(JwtTokenError.VERIFICATION_EXCEPTION, ex);
        }

        return result;
    }
}
