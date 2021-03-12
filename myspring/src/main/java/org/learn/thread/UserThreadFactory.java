package org.learn.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 */
public class UserThreadFactory implements ThreadFactory{

    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public UserThreadFactory(){
        namePrefix = "UserThreadFactory's"+"-worker-";
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = namePrefix + threadNumber.getAndIncrement();
        Thread t = new Thread(null,r,name);
        if(t.isDaemon()){
            t.setDaemon(false);
        }
        if(t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);

        return t;
    }
}
