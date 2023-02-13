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
import java.util.Objects;

@Aspect
@Component
@Slf4j
@Order(0)
public class RequestLoggingAspect {

    /**
     * 프로젝트 패키지내에 있는 controller들의 메소드를 수행할때 동작한다.
     * @param joinPoint 적용할 joinpoint
     * @return 실제 결과
     */
    @Around("execution(* org.anotherclass.colortherock.domain.*.controller.*.*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        log.info("Received request: METHOD : {} URI : {}  ", request.getMethod(), request.getRequestURI());
        Object result = joinPoint.proceed();
        log.info("Returning response: {}", result.toString());
        return result;
    }
}
