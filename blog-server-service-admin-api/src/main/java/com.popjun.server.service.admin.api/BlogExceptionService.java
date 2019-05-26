package com.popjun.server.service.admin.api;

import com.popjun.server.domain.admin.BlogExceptionLog;

public interface BlogExceptionService {
    void insertBlogExceptionLog(BlogExceptionLog blogExceptionLog);
}
