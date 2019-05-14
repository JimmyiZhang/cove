package com.carbybus.infrastructure.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.carbybus.infrastructure.configuration.UniteJwtConfig;
import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.JwtTokenError;
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
    private static UniteJwtConfig config;

    @Autowired
    public JwtUtils(UniteJwtConfig config) {
        JwtUtils.config = config;
    }

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
    public static String create(String id) {
        String tokenSecret = config.getTokenSecret();
        if (StringUtils.isEmpty(tokenSecret)) {
            throw new BusinessException(JwtTokenError.INVALID_SECRET);
        }

        Date now = Date.from(Instant.now());
        Date exp = Date.from(Instant.now().plus(config.getTokenExpired(), ChronoUnit.MINUTES));
        String JwtId = UUID.randomUUID().toString();
        String token = StringConstants.EMPTY;
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenSecret);
            token = JWT.create()
                    .withIssuer(config.getTokenIssue())
                    .withIssuedAt(now)
                    .withSubject(config.getTokenSubject())
                    .withExpiresAt(exp)
                    .withJWTId(JwtId)
                    .withClaim(config.getTokenClaim(), id)
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new BusinessException(JwtTokenError.CREATION_EXCEPTION, ex);
        }
        return token;
    }

    /**
     * token解密
     *
     * @param token token串
     * @return id
     * @author jimmy.zhang
     * @date 2019-04-03
     */
    public static String decode(String token) {
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
    public static boolean verify(String token) {
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
}
