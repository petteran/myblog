package com.petter.myblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author anpetter
 * @ProjectName myblog
 * @ClassName JwtToken
 * @DescClass TODO
 * @CreateTime 2021/3/19 17:07
 * @MyWords 要努力, 要奋斗, 要工作!!!
 */

public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
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
