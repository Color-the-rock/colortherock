package org.anotherclass.colortherock.global.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;


/**
 * 프록시 객체를 bean으로 등록함
 * @author suker80
 */
@Component
@Aspect
public class ApiQueryCounterAspect {

    private final ApiQueryCounter apiQueryCounter;

    public ApiQueryCounterAspect(final ApiQueryCounter apiQueryCounter) {
        this.apiQueryCounter = apiQueryCounter;
    }


    /**
     * DataSource에서 getConnection을 하면 Connection 프록시 객체를 생성한다.
     * @param proceedingJoinPoint 적용할 조인포인트
     * @return 생성된 Connection 프록시 객체
     * @throws Throwable 오류
     */
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