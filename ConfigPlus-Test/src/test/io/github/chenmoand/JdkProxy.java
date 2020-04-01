package io.github.chenmoand;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

    private Object object;

    public Object bind(Object object) {
        this.object = object;
        final Class<?> aClass = object.getClass();
        return Proxy.newProxyInstance(
                aClass.getClassLoader(),
                aClass.getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res;
        System.out.println("前置通知");
        res = method.invoke(object, args);
        System.out.println("后置通知");
        return res;
    }
}
