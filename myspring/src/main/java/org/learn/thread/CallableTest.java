package org.learn.thread;

import java.util.concurrent.Callable;

public class CallableTest implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("call...");
        return null;
    }

    public static void main(String[] args) {
        try {
            Callable callable = new CallableTest();
            System.out.println("wait...");

            callable.call();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
