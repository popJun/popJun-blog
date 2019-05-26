package com.popjun.sso.cas.server.custom.handler;


import com.alibaba.dubbo.config.annotation.Reference;
import com.popjun.annotation.NeedLog;
import com.popjun.constants.Constant;
import com.popjun.server.domain.sso.BlogUser;
import com.popjun.server.service.admin.api.BlogRoleService;
import com.popjun.server.service.admin.api.BlogUserService;

import com.popjun.sso.cas.server.config.captcha.RememberMeUsernamePasswordCaptchaCredential;
import com.popjun.utils.PasswordSaltUtil;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

@Component

public class TotalHandler  extends AbstractPreAndPostProcessingAuthenticationHandler {
    @Autowired
    private RememberMeUsernamePasswordCaptchaAuthenticationHandler captchaAuthenticationHandler;
    @Autowired
    private ShiroAuthenticationHandler shiroAuthenticationHandler;
    @Reference(version =  Constant.Dubbo.DUBBO_VERSION_ADMIN)
    BlogUserService blogUserService;
    @Reference(version =  Constant.Dubbo.DUBBO_VERSION_ADMIN)
    BlogRoleService blogRoleService;
    public TotalHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        final RememberMeUsernamePasswordCaptchaCredential myCredential = (RememberMeUsernamePasswordCaptchaCredential) credential;
        myCredential.setPassword(PasswordSaltUtil.passwordAddSalt(myCredential.getUsername(),myCredential.getPassword()));
        configHandler(myCredential);
        return createHandlerResult(credential, this.principalFactory.createPrincipal(myCredential.getUsername(),getUserInfo(myCredential)));
    }

    private void configHandler(RememberMeUsernamePasswordCaptchaCredential myCredential) throws GeneralSecurityException{
        //由于cas加载认证器只要有一个成功就会登录成功，所以需要把认证信息写在一个handle中
        captchaAuthenticationHandler.doAuthentication(myCredential);
        shiroAuthenticationHandler.authenticateUsernamePasswordInternal(myCredential);
    }
    private Map getUserInfo(RememberMeUsernamePasswordCaptchaCredential myCredential){
        BlogUser user = blogUserService.findUserByLoginName(myCredential.getUsername());
        Map result = new HashMap();
        result.put("nikeName",user.getNickName());
        result.put("loginName",user.getLoginName());
        result.put("roles",blogRoleService.getRoleNameByUserId(user.getId()));
        result.put("loginTime",user.getLastLoginTime());
        result.put("createTime",user.getCreateTime());
        return result;
    }
    @Override
    public boolean supports(Credential credential) {
        if (!RememberMeUsernamePasswordCaptchaCredential.class.isInstance(credential)) {
            return false;
        } else if (this.credentialSelectionPredicate == null) {
            return true;
        } else {
            boolean result = this.credentialSelectionPredicate.test(credential);
            return result;
        }
    }
}
