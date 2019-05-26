package com.popjun.sso.cas.server.custom.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.popjun.annotation.NeedLog;
import com.popjun.constants.Constant;
import com.popjun.server.domain.sso.BlogUser;
import com.popjun.server.service.admin.api.BlogUserService;
import com.popjun.sso.cas.server.config.captcha.RememberMeUsernamePasswordCaptchaCredential;
import com.popjun.sso.cas.server.custom.exception.CaptchaErrorException;
import com.popjun.sso.cas.server.custom.exception.MyAccountLockException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;

@Component
public class RememberMeUsernamePasswordCaptchaAuthenticationHandler {
	@Reference(version =  Constant.Dubbo.DUBBO_VERSION_ADMIN)
	BlogUserService blogUserService;

	@NeedLog
	public void doAuthentication(RememberMeUsernamePasswordCaptchaCredential myCredential) throws GeneralSecurityException {
		String newCaptcha = "";
		String captcha = myCredential.getCaptcha();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Object obj = request.getSession().getAttribute("captcha");
		if (obj != null) {
			newCaptcha = obj.toString();
		}
		BlogUser blogUser = blogUserService.login(myCredential.getUsername(), myCredential.getPassword());
		if (blogUser == null) {
			throw new AccountNotFoundException("账号密码错误");
		} else if (!newCaptcha.toLowerCase().equals(captcha.toLowerCase())) {
			throw new CaptchaErrorException("验证码错误");
		} else if (blogUser.getStatus() == 1L) {
			throw new MyAccountLockException("该账号已被锁定");
		}
	}
}
