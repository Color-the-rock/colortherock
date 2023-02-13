package org.anotherclass.colortherock.global.log;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * PreparedStatement를 감싼 프록시 객체가 수행될때 동작하는 핸들러
 * @see java.sql.PreparedStatement
 */

public class PreparedStatementInvocationHandler implements InvocationHandler {

    private final Object preparedStatement;
    private final ApiQueryCounter apiQueryCounter;

    public PreparedStatementInvocationHandler(final Object preparedStatement,
                                              final ApiQueryCounter apiQueryCounter) {
        this.preparedStatement = preparedStatement;
        this.apiQueryCounter = apiQueryCounter;
    }

    /**
     * method 이름이 excute 쿼리가 실행 될 때만 카운팅을 한다.
     * @param method 메소드 이름
     * @param args 매개 변수
     * @return 쿼리 실행 결과
     */
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        if (method.getName().contains("execute") && RequestContextHolder.getRequestAttributes() != null) {
            apiQueryCounter.increaseCount();
        }
        return method.invoke(preparedStatement, args);
    }
}

