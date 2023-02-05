package org.anotherclass.colortherock.global.log;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConnectionInvocationHandler implements InvocationHandler {

    private final Object connection;
    private final ApiQueryCounter apiQueryCounter;

    public ConnectionInvocationHandler(final Object connection, final ApiQueryCounter apiQueryCounter) {
        this.connection = connection;
        this.apiQueryCounter = apiQueryCounter;
    }

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

