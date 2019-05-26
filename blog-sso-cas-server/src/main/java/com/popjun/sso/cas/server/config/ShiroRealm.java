package com.popjun.sso.cas.server.config;


import com.popjun.annotation.NeedLog;
import com.popjun.server.domain.sso.BlogUser;
import com.popjun.server.service.admin.api.BlogUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 创建Shiro
 */
@NeedLog
public class ShiroRealm  extends AuthorizingRealm  {

    @Autowired
    private BlogUserService blogUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        BlogUser user = blogUserService.login(token.getUsername(), new String(token.getPassword()));
        if(null == user){
            throw new UnknownAccountException("账号密码错误");
        }
        //0表示用户被锁定，1表示正常
        if ("0".equals(user.getStatus())){
            throw  new LockedAccountException("账号被锁定");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(),user.getPswd(),getName());
    }
}
