package com.popjun.server.admin.persist.dao;


import com.popjun.server.domain.sso.BlogUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogUserMapper{
    List<BlogUser> getBlogUser(@Param("loginName") String loginName, @Param("pwd") String pwd);

}