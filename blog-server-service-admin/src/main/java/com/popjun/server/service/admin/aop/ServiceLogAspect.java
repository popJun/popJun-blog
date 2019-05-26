package com.popjun.server.service.admin.aop;

import cn.hutool.json.JSONUtil;
import com.popjun.annotation.NeedLog;
import com.popjun.server.admin.persist.dao.BlogExceptionLogMapper;
import com.popjun.server.domain.admin.BlogExceptionLog;
import com.popjun.utils.LogUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Log4j2
public class ServiceLogAspect {

    @Autowired
    private BlogExceptionLogMapper blogExceptionLogMapper;
    //切面
    @Pointcut("@annotation(needLog)")
    public void serviceLog(NeedLog needLog){}

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("serviceLog(needLog)")
    public void doBefore(JoinPoint joinPoint, NeedLog needLog){
        LogUtil.insertBefore(joinPoint);
    }

    @AfterReturning(value = "serviceLog(needLog)",returning = "returnValue")
    public void doAfterReturning(JoinPoint joinPoint,Object returnValue,NeedLog needLog){
        LogUtil.insertAfterReturning(returnValue);
    }

    @AfterThrowing(value = "serviceLog(needLog)",throwing = "e")
    public void doAfterThrowing(NeedLog needLog,Throwable e){
        LogUtil.insertException(e);
        BlogExceptionLog blogExceptionLog = new BlogExceptionLog(0L, JSONUtil.toJsonStr(e),e.getMessage(),new Date());
        blogExceptionLogMapper.insert(blogExceptionLog);
    }
    /*@Around(value = "serviceLog(needLog)")
    public void doAround(ProceedingJoinPoint pjp,NeedLog needLog){
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            LogUtil.insertException(throwable);
            BlogExceptionLog blogExceptionLog = new BlogExceptionLog(0L, JSONUtil.toJsonStr(throwable),throwable.getMessage(),new Date());
            blogExceptionLogMapper.insert(blogExceptionLog);
        }
    }*/
}
