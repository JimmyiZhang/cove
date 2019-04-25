package com.carbybus.infrastructure.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.carbybus.infrastructure.exception.BusinessException;
import com.carbybus.infrastructure.exception.JwtTokenError;

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
public class JwtUtils {
    /**
     * 工具类使用私有构造器覆盖公共构造器，防止公共构造器被调用
     * Sonar Code smell Major squid:S1118
     */
    private JwtUtils() {
    }

    /**
     * 密钥
     */
    private final static String TOKEN_SECRET = "7p9izREdj3cAAQR7HyDCoMDv9dDDtzQLbJ1ScicADAclc";
    private final static String TOKEN_ISSUE = "carbybus";
    private final static String TOKEN_SUBJECT = "user";
    private final static String TOKEN_CLAIM = "id";

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
        Date now = Date.from(Instant.now());
        Date exp = Date.from(Instant.now().plus(30, ChronoUnit.DAYS));
        String JwtId = UUID.randomUUID().toString();
        String token = StringConstants.EMPTY;
        try {
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            token = JWT.create()
                    .withIssuer(TOKEN_ISSUE)
                    .withIssuedAt(now)
                    .withSubject(TOKEN_SUBJECT)
                    .withExpiresAt(exp)
                    .withJWTId(JwtId)
                    .withClaim(TOKEN_CLAIM, id)
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
        String id = StringConstants.EMPTY;
        try {
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(TOKEN_ISSUE)
                    .acceptExpiresAt(5)
                    .build();

            verifier.verify(token);

            DecodedJWT code = JWT.decode(token);
            id = code.getClaim(TOKEN_CLAIM).asString();
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
        boolean verify;
        try {
            Algorithm algorithm = Algorithm.HMAC512(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(TOKEN_ISSUE)
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
