package com.popjun.server.admin.persist.dao;


import com.popjun.server.domain.sso.BlogRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface BlogRoleMapper{
    Set<BlogRole> getRoleByUserId(@Param("userId") Long userId);
    Set<BlogRole> getAllRole();
}