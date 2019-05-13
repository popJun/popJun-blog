package com.popjun.sso.cas.server.custom.exception;

import javax.security.auth.login.AccountException;

/**
 * 自定义异常
 * 用户没找到异常
 */
public class MyAccountLockException extends AccountException{
    public MyAccountLockException(){
        super();
    }
    public MyAccountLockException(String msg) {
        super(msg);
    }
}
