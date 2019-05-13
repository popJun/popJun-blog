package com.popjun.sso.cas.server.config.captcha;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

import javax.validation.constraints.Size;

/**
 * 继承rememberMe的凭证添加验证码
 */
public class RememberMeUsernamePasswordCaptchaCredential extends RememberMeUsernamePasswordCredential {
    @Size(min = 5, max = 5, message = "require captcha")
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(this.captcha).toHashCode();
    }

}
