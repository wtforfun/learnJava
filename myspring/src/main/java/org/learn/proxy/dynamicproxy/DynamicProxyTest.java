package org.learn.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    public static void main(String[] args) {
        //真正实现类
        Worker worker = new Worker();
        //代理类
        InvocationHandler invocationHandler = new DynamicWorkerProxy(worker);
        //接口调用类——最终用来调用被代理的接口
        IWork proxyWorker = (IWork)Proxy.newProxyInstance(Worker.class.getClassLoader(),worker.getClass().getInterfaces(),invocationHandler);
        //调用接口
        proxyWorker.work();
    }
}
