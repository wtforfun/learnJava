package org.learn.thread;

public class ThreadTest extends Thread {

    @Override
    public void run() {
        
        System.out.println("run..."+this.getId());
    }
}
