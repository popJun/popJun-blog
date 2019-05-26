package com.popjun.sso.cas.server.custom.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.popjun.annotation.NeedLog;
import com.popjun.constants.Constant;
import com.popjun.server.domain.sso.BlogUser;
import com.popjun.server.service.admin.api.BlogRoleService;
import com.popjun.server.service.admin.api.BlogUserService;
import com.popjun.sso.cas.server.config.captcha.RememberMeUsernamePasswordCaptchaCredential;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.exceptions.AccountDisabledException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.Set;

@Component
public class ShiroAuthenticationHandler {
    @Reference(version =  Constant.Dubbo.DUBBO_VERSION_ADMIN)
    BlogRoleService blogRoleService;
    @Reference(version =  Constant.Dubbo.DUBBO_VERSION_ADMIN)
    BlogUserService blogUserService;
    protected void authenticateUsernamePasswordInternal(RememberMeUsernamePasswordCaptchaCredential myCredential) throws GeneralSecurityException{
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(myCredential.getUsername(), myCredential.getPassword());
            if (myCredential instanceof RememberMeUsernamePasswordCaptchaCredential) {
                token.setRememberMe(RememberMeUsernamePasswordCaptchaCredential.class.cast(myCredential).isRememberMe());
            }
            checkSubRoleAndPermission(myCredential);
        }  catch (final UnknownAccountException uae) {
            throw new AccountNotFoundException(uae.getMessage());
        } catch (final IncorrectCredentialsException ice) {
            throw new FailedLoginException(ice.getMessage());
        } catch (final LockedAccountException | ExcessiveAttemptsException lae) {
            throw new AccountLockedException(lae.getMessage());
        } catch (final ExpiredCredentialsException eae) {
            throw new CredentialExpiredException(eae.getMessage());
        } catch (final DisabledAccountException eae) {
            throw new AccountDisabledException(eae.getMessage());
        } catch (AccountException e) {
            throw new AccountException(e.getMessage());
        }
    }


    /**
     * 检查当前用户是否具有权限
     */
    protected void checkSubRoleAndPermission(UsernamePasswordCredential credential) {
        BlogUser blogUser = blogUserService.login(credential.getUsername(), credential.getPassword());
        if (blogRoleService.checkRoleByUserId(blogUser.getId())){
            return;
        }
        throw new AuthenticationException("没有权限访问");
    }
}
