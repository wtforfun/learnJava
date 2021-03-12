package org.learn.synchronizedemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * 已经获得了锁的线程可以再次获得同样的锁
 * 如果锁不可重入，那么methodA调用methodB的时候，需要再次获得该锁
 * 但是methodA还没有释放锁，methodB永远不能获得该锁，将造成死锁
 */
public class ReentrantDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();


//        synchronized (ReentrantDemo.class) {
//            System.out.println("当前线程获得了锁..");
//            while (true) {
//                synchronized (ReentrantDemo.class) {
//                    System.out.println("当前线程再次获得了同样的锁...");
//                }
//
//                try {
//                    Thread.sleep(500);
//                } catch (Exception e) {
//
//                }
//
//            }
//        }
        ReentrantDemo reentrantDemo = new ReentrantDemo();
        reentrantDemo.methodA();
    }

    public synchronized void methodA() {
        System.out.println("methodA 获得了锁");
        methodB();
    }

    public synchronized void methodB() {
        System.out.println("methodB 获得了锁");

    }
}
