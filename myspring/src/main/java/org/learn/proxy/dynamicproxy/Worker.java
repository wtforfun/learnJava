package org.learn.proxy.dynamicproxy;

public class Worker implements IWork {

    @Override
    public void work() {
        System.out.println("worker is working..");
    }
}
