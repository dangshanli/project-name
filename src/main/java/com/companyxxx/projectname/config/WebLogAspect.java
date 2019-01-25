package com.companyxxx.projectname.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author: luzj
 * @date: 2019-01-25
 * @description: 使用aop进行controller的统一日志拦截
 */
@Aspect
@Component
@Order(6)//切面执行顺序，数值越低越优先
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<>();//存储请求消耗时间

    /**
     * 定义切点
     * 拦截controller package下所有的class和method的执行
     */
    @Pointcut("execution(public * com.companyxxx.projectname.controller..*.*(..))")
    public void webLog() {
    }

    //拦截webLog切点，打印请求信息
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());//请求开始时间
        //获取request对象，从中拿取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 请求内容
        logger.info("URL: " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD: " + request.getMethod());
        logger.info("IP: " + request.getRemoteAddr());
        logger.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求,返回内容
        logger.info("RESPONSE: " + ret);
        //记录请求总时间
        logger.info("SPEND TIME: " + (System.currentTimeMillis() - startTime.get()));
    }
}



