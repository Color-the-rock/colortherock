package org.anotherclass.colortherock.global.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
@Order(0)
public class RequestLoggingAspect {

    @Around("execution(* org.anotherclass.colortherock.domain.*.controller.*.*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("Received request: METHOD : {} URI : {}  ", request.getMethod(), request.getRequestURI());
        Object result = joinPoint.proceed();
        log.info("Returning response: {}", result.toString());
        return result;
    }
}
