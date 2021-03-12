package org.learn.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicWorkerProxy implements InvocationHandler{

    private Object object;

    public DynamicWorkerProxy(Object o){
        object = o;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println( "proxy is looking...");
        method.invoke(object,args);
        return null;
    }
}
