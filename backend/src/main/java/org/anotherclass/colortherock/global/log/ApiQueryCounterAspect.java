package org.anotherclass.colortherock.global.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApiQueryCounterAspect {

    private final ApiQueryCounter apiQueryCounter;

    public ApiQueryCounterAspect(final ApiQueryCounter apiQueryCounter) {
        this.apiQueryCounter = apiQueryCounter;
    }

    @Around("execution(* javax.sql.DataSource.getConnection())")
    public Object getConnection(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final Object connection = proceedingJoinPoint.proceed();
        return Proxy.newProxyInstance(
                connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(),
                new ConnectionInvocationHandler(connection, apiQueryCounter)
        );
    }
}