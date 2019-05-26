package com.popjun.server.web.admin.aop;

import cn.hutool.json.JSONUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.popjun.annotation.NeedLog;
import com.popjun.constants.Constant;
import com.popjun.server.domain.admin.BlogExceptionLog;
import com.popjun.server.service.admin.api.BlogExceptionService;
import com.popjun.utils.LogUtil;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Log4j
public class WebAspect {
    @Reference(version = Constant.Dubbo.DUBBO_VERSION_ADMIN)
    BlogExceptionService blogExceptionService;

    //切面
    @Pointcut("@annotation(needLog)")
    public void webLog(NeedLog needLog){}
    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("webLog(needLog)")
    public void doBefore(JoinPoint joinPoint, NeedLog needLog){
        LogUtil.insertWebBefore(joinPoint);

    }

    @AfterReturning(value = "webLog(needLog)",returning = "returnValue")
    public void doAfterReturning(Object returnValue,NeedLog needLog){
        LogUtil.insertAfterReturning(returnValue);
    }

    @AfterThrowing(value = "webLog(needLog)",throwing = "e")
    public void doAfterThrowing(Throwable e,NeedLog needLog){
        LogUtil.insertException(e);
        BlogExceptionLog blogExceptionLog = new BlogExceptionLog(0L, JSONUtil.toJsonStr(e),e.getMessage(),new Date());
        blogExceptionService.insertBlogExceptionLog(blogExceptionLog);
    }

}
