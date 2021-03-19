package com.petter.myblog.shiro;

import com.petter.myblog.entity.User;
import com.petter.myblog.service.UserService;
import com.petter.myblog.util.JwtUtils;
import net.sf.saxon.expr.instruct.ValueOf;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anpetter
 * @ProjectName myblog
 * @ClassName AccountRealm
 * @DescClass TODO
 * @CreateTime 2021/3/19 16:30
 * @MyWords 要努力, 要奋斗, 要工作!!!
 */

/**
 * AccountRealm是shiro进行登录或者权限校验的逻辑所在
 */

@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    /**
     * 为了让realm支持jwt的凭证校验
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 权限校验
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证校验
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            return (AuthenticationInfo) new UnknownAccountException("账号不存在");
        }

        if (user.getStatus() == -1) {
            return (AuthenticationInfo) new LockedAccountException("账号已经被锁定");
        }

        //获取可以公开的用户信息
        AccountProfile accountProfile = new AccountProfile();
        //复制属性到 accountProfile
        BeanUtils.copyProperties(user, accountProfile);
        return new  SimpleAuthenticationInfo(accountProfile,jwtToken.getCredentials(),getName());
    }

}
