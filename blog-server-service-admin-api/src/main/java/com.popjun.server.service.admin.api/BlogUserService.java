package com.popjun.server.service.admin.api;


import com.popjun.server.domain.sso.BlogUser;

public interface BlogUserService {
    /**
     * 用于登录
     * @param loginName
     * @param pwd
     * @return
     */
     BlogUser login(String loginName, String pwd);

    /**
     * 通过登录名找寻用户
     * @param loginName
     * @return
     */
     BlogUser findUserByLoginName(String loginName);
}
