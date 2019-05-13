package com.popjun.server.service.admin.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.popjun.constants.Constant;
import com.popjun.server.admin.persist.dao.BlogUserMapper;
import com.popjun.server.domain.sso.BlogUser;
import com.popjun.server.service.admin.api.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(version = Constant.Dubbo.DUBBO_VERSION_ADMIN, interfaceClass = BlogUserService.class,retries = 0)
@Component
public class BlogUserServiceImpl implements BlogUserService {
    @Autowired
    private BlogUserMapper blogUserMapper;

    @Override
    @Transactional
    public BlogUser login(String loginName, String pwd) {
        List<BlogUser> blogUsers = blogUserMapper.getBlogUser(loginName, pwd);
        return Optional.ofNullable(blogUsers).map(list -> list.stream().findFirst().orElse(null)).orElse(null);
    }

    @Override
    @Transactional
    public BlogUser findUserByLoginName(String loginName) {
        List<BlogUser> blogUsers = blogUserMapper.getBlogUser(loginName,null);
        return Optional.ofNullable(blogUsers).map(list -> list.stream().findFirst().orElse(null)).orElse(null);
    }


}
