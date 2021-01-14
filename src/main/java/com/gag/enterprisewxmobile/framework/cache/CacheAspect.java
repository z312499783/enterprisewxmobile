package com.gag.enterprisewxmobile.framework.cache;


import com.gag.enterprisewxmobile.framework.config.SpringContextUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 记录Cacheable中的value
 */
@Aspect
@Slf4j
class CacheAspect{

    @Pointcut("@annotation(org.springframework.cache.annotation.Cacheable)")
    public void cacheable() {
    }

    @Before("cacheable()")
    public void  methodBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //打印请求内容
        log.info("---------------请求内容---------------");
        log.info("请求类方法:"+joinPoint.getSignature().getName());
        log.info("请求类方法参数:"+ Arrays.toString(joinPoint.getArgs()));
        log.info("---------------请求内容---------------");
    }

    @AfterReturning(returning = "o",pointcut = "cacheable()")
    public void methodAfterReturning(Object o){
        log.info("===============返回内容===============");
        log.info("返回的内容:"+ o.toString());
        log.info("===============返回内容===============");
    }


    @AfterThrowing(pointcut = "cacheable()",throwing = "e")
    public void logThrowing(JoinPoint joinPoint,Throwable e){
        log.info("***************抛出异常***************");

        log.info("请求类方法:"+joinPoint.getSignature().getName());
        log.info("异常内容:"+e);
        log.info("***************抛出异常***************");
    }
}
