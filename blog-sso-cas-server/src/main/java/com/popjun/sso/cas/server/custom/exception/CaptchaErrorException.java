package com.popjun.sso.cas.server.custom.exception;

import javax.security.auth.login.AccountException;

public class CaptchaErrorException extends AccountException{
    public CaptchaErrorException(){

    }
    public CaptchaErrorException(String msg){
        super(msg);
    }
}
