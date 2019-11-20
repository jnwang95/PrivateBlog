package com.wjn.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description: JWTToken
 * @author: jnWang
 * @create: 2019-11-20 15:14
 */
public class JwtToken implements AuthenticationToken {
    /**
     * Token
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
