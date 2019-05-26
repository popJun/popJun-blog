package com.popjun.server.service.admin.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.popjun.annotation.NeedLog;
import com.popjun.constants.Constant;
import com.popjun.server.admin.persist.dao.BlogRoleMapper;
import com.popjun.server.domain.sso.BlogRole;
import com.popjun.server.service.admin.api.BlogRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service(version = Constant.Dubbo.DUBBO_VERSION_ADMIN, interfaceClass = BlogRoleService.class,retries = 0)
@org.springframework.stereotype.Service

public class BlogRoleServiceImpl implements BlogRoleService{
    @Autowired
    private BlogRoleMapper blogRoleMapper;

    @Override
    @NeedLog
    public Boolean checkRoleByUserId(Long userId) {
        //当前用户所归属的角色
        Set<BlogRole> currenRoles = blogRoleMapper.getRoleByUserId(userId);
        return  Optional.ofNullable(currenRoles).map(role ->true).orElse(false);
    }
    @Override
    @NeedLog
    public List<String> getRoleNameByUserId(Long userId) {
        return blogRoleMapper.getRoleByUserId(userId).stream().map(BlogRole::getRoleName).collect(Collectors.toList());
    }
}
