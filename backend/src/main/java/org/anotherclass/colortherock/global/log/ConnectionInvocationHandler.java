package org.anotherclass.colortherock.global.log;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Connection 객체를 가져왔을때 수행하는 InvocationHandler
 */
public class ConnectionInvocationHandler implements InvocationHandler {

    private final Object connection;
    private final ApiQueryCounter apiQueryCounter;

    public ConnectionInvocationHandler(final Object connection, final ApiQueryCounter apiQueryCounter) {
        this.connection = connection;
        this.apiQueryCounter = apiQueryCounter;
    }

    /**
     * PreparedStatement를 가져올 떄 프록시 객체를 반환한다.
     * @param o 타깃
     * @param method 수행하는 메서드
     * @param args 변수
     * @return preparedStatement 객체 or 프록시 객체
     */
    @Override
    public Object invoke(final Object o, final Method method, final Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        final Object result = method.invoke(connection, args);
        if (method.getName().equals("prepareStatement")) {
            return Proxy.newProxyInstance(
                    result.getClass().getClassLoader(),
                    result.getClass().getInterfaces(),
                    new PreparedStatementInvocationHandler(result, apiQueryCounter)
            );
        }
        return result;
    }
}

