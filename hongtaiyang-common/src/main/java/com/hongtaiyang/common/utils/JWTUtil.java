package com.hongtaiyang.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hongtaiyang.common.constant.TerminalTypeConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author ：tangtuo
 * @date ：Created in 2020/4/24 15:48
 * @description：jsonwebtoken 工具类
 * @version: 1.0.0
 */
@Slf4j
public class JWTUtil {

    /**
     * token过期时间  客户端:14天   技师端:14天  后台:2小时
     */
    private static final long ADMIN_EXPIRE_TIME = 2 * 60 * 60 * 1000;
    private static final long CLIENT_EXPIRE_TIME = 14 * 24 * 60 * 60 * 1000;
    private static final long ARTIFICER_EXPIRE_TIME = 14 * 24 * 60 * 60 * 1000;

    /**
     * 加盐
     */
    private static final String SECRET = "jwt";

    /**
     * 生成签名
     *
     * @param userId 用户id
     * @return 加密的token
     */
    public static String createToken(String userId, String terminalType) {
        long timestamp = System.currentTimeMillis();
        // 生成token
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("terminalType", terminalType)
                .withClaim("timeStamp", timestamp)
                .withExpiresAt(new Date(timestamp + getExpireTimeByTerminalType(terminalType)))
                .sign(Algorithm.HMAC256(userId + SECRET));

    }

    /**
     * 校验token
     *
     * @return 是否正确
     */
    public static boolean verify(String signature, String userId, String tenantId) {
        try {
            // 根据密码生成JWT效验器
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(userId + SECRET))
                    .withClaim("userId", userId)
                    .withClaim("tenantId", tenantId)
                    .withClaim("timeStamp", JWT.decode(signature).getClaim("timeStamp").asLong())
                    .build();
            // 效验TOKEN，认证失败时会抛异常
            verifier.verify(signature);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 获得签名中包含的userId
     */
    public static Integer getUserId(String signature) {
        try {
            DecodedJWT jwt = JWT.decode(signature);
            return new Integer(jwt.getClaim("userId").asString());
        } catch (JWTDecodeException e) {
            log.error(e.getMessage(), e);
            return null;

        }
    }

    /**
     * 获得签名中包含的tenantId
     */
    public static String getTerminalType(String signature) {
        try {
            DecodedJWT jwt = JWT.decode(signature);
            return jwt.getClaim("terminalType").asString();
        } catch (JWTDecodeException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }


    /**
     * 是否过期，抛出异常说明签名错误，返回false表示和过期无关
     */
    public static boolean isExpire(String signature) {
        try {
            Date date = JWT.decode(signature).getExpiresAt();
            return date.getTime() < System.currentTimeMillis();
        } catch (JWTDecodeException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    private static Long getExpireTimeByTerminalType(String terminalType) {
        Long time;
        switch (terminalType) {
            case TerminalTypeConstant.TYPE_ADMIN:
                time = ADMIN_EXPIRE_TIME;
                break;
            case TerminalTypeConstant.TYPE_ARTIFICER:
                time = ARTIFICER_EXPIRE_TIME;
                break;
            case TerminalTypeConstant.TYPE_CLIENT:
                time = CLIENT_EXPIRE_TIME;
                break;
            default:
                throw new RuntimeException("unknown terminalType");
        }
        return time;
    }

    public static void main(String[] args) {
        String admin = createToken("12", "admin");
        System.out.println(getUserId(admin));
        String userId = getTerminalType(admin);
        System.out.println(userId);
    }


}
