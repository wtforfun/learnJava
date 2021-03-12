package org.learn.thread;

/**
 * java中的sleep()和wait()的区别
 * sleep不释放锁
 * wait释放锁
 *
 * @author Hongten
 * @date 2013-12-10
 */
public class SleepWaitDemo {

    public static void main(String[] args) {

        new Thread(new Thread1()).start();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (SleepWaitDemo.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    SleepWaitDemo.class.wait();
                    System.out.println("thread1 is notified!!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (SleepWaitDemo.class) {
                System.out.println("enter thread2....");
                System.out.println("thread2 is sleep....");
                try {
                    //在调用sleep()方法的过程中，线程不会释放对象锁。
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");
                //如果我们把代码：SleepWaitDemo.class.notify();给注释掉，即TestD.class调用了wait()方法，但是没有调用notify()
                //方法，则线程永远处于挂起状态。
                SleepWaitDemo.class.notify();
            }
        }
    }
}