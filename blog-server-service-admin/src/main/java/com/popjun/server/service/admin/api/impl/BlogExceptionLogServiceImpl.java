package com.popjun.server.service.admin.api.impl;


import com.popjun.annotation.NeedLog;
import com.popjun.constants.Constant;
import com.popjun.server.admin.persist.dao.BlogExceptionLogMapper;
import com.popjun.server.domain.admin.BlogExceptionLog;
import com.popjun.server.service.admin.api.BlogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@com.alibaba.dubbo.config.annotation.Service(version = Constant.Dubbo.DUBBO_VERSION_ADMIN, interfaceClass = BlogExceptionService.class,retries = 0)
public class BlogExceptionLogServiceImpl implements BlogExceptionService {
    @Autowired
    private BlogExceptionLogMapper blogExceptionLogMapper;

    @Override
    @NeedLog
    public void insertBlogExceptionLog(BlogExceptionLog blogExceptionLog) {
            blogExceptionLogMapper.insert(blogExceptionLog);
    }
}
