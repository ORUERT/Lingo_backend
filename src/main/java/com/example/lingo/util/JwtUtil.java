package com.example.lingo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties(prefix = "jwt")
@Component
@Slf4j
public class JwtUtil {
    // 秘钥
    @Setter
    private String secret;
    private static final long TIME_UNIT = 1000;

    // 生成包含用户id的token
    public String createJwtToken(String userId, long expireTime) {
        Date date = new Date(System.currentTimeMillis() + expireTime * TIME_UNIT);
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("userId", userId)
                .withExpiresAt(date) // 设置过期时间q
                .sign(algorithm);     // 设置签名算法
    }

    // 校验token，其实就是比较token
    public boolean verifyToken(String token,String userId) {
        try {
            //检测是否能够成功解码token
            JWT.require(Algorithm.HMAC256(secret)).withClaim("userId", userId).build().verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    // 从token中获取用户id
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    // 判断 token 是否过期
    public boolean isExpire(String token) {
        DecodedJWT jwt = JWT.decode(token);
        // 如果token的过期时间小于当前时间，则表示已过期，为true
        return jwt.getExpiresAt().getTime() < System.currentTimeMillis();
    }

}
