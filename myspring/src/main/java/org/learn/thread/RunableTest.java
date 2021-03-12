package org.learn.thread;

public class RunableTest implements Runnable{

    @Override
    public void run() {
        System.out.println("run...");
    }

    public static void main(String[] args) {
        Runnable runnable = new RunableTest();
        runnable.run();
    }
}
