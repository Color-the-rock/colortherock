package org.anotherclass.colortherock.global.log;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PreparedStatementInvocationHandler implements InvocationHandler {

    private final Object preparedStatement;
    private final ApiQueryCounter apiQueryCounter;

    public PreparedStatementInvocationHandler(final Object preparedStatement,
                                              final ApiQueryCounter apiQueryCounter) {
        this.preparedStatement = preparedStatement;
        this.apiQueryCounter = apiQueryCounter;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        if (method.getName().contains("execute") && RequestContextHolder.getRequestAttributes() != null) {
            apiQueryCounter.increaseCount();
        }
        return method.invoke(preparedStatement, args);
    }
}

