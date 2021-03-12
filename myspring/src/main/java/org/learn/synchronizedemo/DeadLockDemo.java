package org.learn.synchronizedemo;

/**
 * 死锁样例 两个线程，互相持有对象有已经拥有的锁，并且在获取对方的锁之后，才会释放自己的锁
 */
public class DeadLockDemo {

    public static String obj1 = "obj1";

    public static String obj2 = "obj2";


    public static void main(String[] args) {

        Thread thread1 = new Thread(new Thread1());
        Thread thread2 = new Thread(new Thread2());
        thread1.start();
        thread2.start();

    }



}

class Thread2 implements Runnable{
    @Override
    public void run() {
        while (true){
            synchronized (DeadLockDemo.obj2){
                System.out.println("Thread2获得了obj2");
                try {
                    Thread.sleep(3000);
                }catch (Exception e){

                }
                synchronized (DeadLockDemo.obj1){
                    System.out.println("Thread2获得了obj1");
                }
            }
        }

    }
}

class Thread1 implements Runnable{
    @Override
    public void run() {
        while (true) {
            synchronized (DeadLockDemo.obj1) {
                System.out.println("Thread1获得了obj1");
                try {
                    Thread.sleep(3000);
                }catch (Exception e){

                }
                synchronized (DeadLockDemo.obj2) {
                    System.out.println("Thread1获得了obj2");
                }
            }
        }
    }
}

