package com.popjun.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.db.sql.SqlBuilder;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Log4j2
public class LogUtil {
    private static Long methodStartTime = 0L;
    private static Long methodEndTime = 0L;

    public static void insertBefore(JoinPoint joinPoint) {
        methodStartTime = System.currentTimeMillis();
        log.info("方法名：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("参数 ：" + Arrays.toString(joinPoint.getArgs()));
        log.info("开始时间：" + DateUtil.format(new Date(methodStartTime), "yyyy-MM-DD hh:mm:ss"));
    }

    public static void insertWebBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        log.info("访问路径："+basePath);
        insertBefore(joinPoint);
    }

    public static void insertAfterReturning(Object returnValue){
        methodEndTime = System.currentTimeMillis();
        log.info("返回参数："+ JSONUtil.toJsonStr(returnValue));
        log.info("结束时间："+ DateUtil.format(new Date(methodEndTime),"yyyy-MM-dd hh:mm:ss"));
        log.info("方法完成时间："+ (methodEndTime-methodStartTime));
    }

    public static void insertException(Throwable e){
        log.error("具体异常："+JSONUtil.toJsonStr(e));
        log.error("异常信息："+e.getMessage());
        log.error("异常发生时间："+DateUtil.format(new Date(),"yyyy-MM-dd hh:mm:ss"));
    }
}
