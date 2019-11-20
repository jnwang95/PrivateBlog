package com.wjn.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: JWT工具类
 * @author: jnWang
 * @create: 2019-11-20 11:49
 */
@Data
@Component
@PropertySource(value = {"classpath:config/config.properties"})
public class JWTUtil {

    /**
     * token过期时间
     */
    @Value("${ttl}")
    private long ttl;
    /**
     * 加密盐
     */
    @Value("${encryptJWTKey}")
    private String key;

    /**
     * 设置认证token
     *      id:登录用户id
     *      subject：登录用户名
     *
     */
    public String createJwt(String username, String password) {
        //1.设置失效时间
        long now = System.currentTimeMillis();//当前毫秒
        long exp = now + ttl;
        //2.创建jwtBuilder
        String token = Jwts.builder().setId(username).setSubject(password)
                .setIssuedAt(new Date())
                .setExpiration(new Date(exp))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return token;
      }

    /**
     * 解析token字符串获取clamis
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }

}
