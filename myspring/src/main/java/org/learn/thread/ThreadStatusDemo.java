package org.learn.thread;

/**
 * java线程有6中状态
 * 1，NEW 新建线程，没有调用start()方法
 * 2，RUNABLE 调用start()方法后，进入RUNABLE(实际包括ready和running)
 * 3，BLOCKED 线程在start之后，由于等待获取锁，而进入BLOCKED状态
 * 4, WAITING 只有在调用了Object.wait()，Thread.join()，LockSupport.park()时，才会进入等待状态
 * 5,
 *
 * @author wangtao
 * @date 2021/3/8 22:36
 */
public class ThreadStatusDemo extends Thread {

    private byte[] lock;

    @Override
    public void run() {

        synchronized (lock) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "done");
        }
    }

    public ThreadStatusDemo(byte[] lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 1，新建线程，未调用start()
         */
        Thread thread = new Thread();
        //NEW
        System.out.println(thread.getState());

        /**
         * 2，调用start()方法，
         * 进入ready状态，等待获取cpu时间分片，即可进入running
         * 调用start()方法，进入ready状态，等待获取cpu时间分片，即可进入running
         */
        thread.start();
        //RUNABLE
        System.out.println(thread.getState());

        byte[] lock = new byte[0];
        ThreadStatusDemo thread1 = new ThreadStatusDemo(lock);
        thread1.start();

        ThreadStatusDemo thread2 = new ThreadStatusDemo(lock);
        thread2.start();

        Thread.sleep(1000);

        /**
         * 3，线程在已经start后，由于等待锁而进入block状态
         */
        System.out.println(thread2.getState());

        /**
         * 4 WAIT 下面演示三种进入wait的情况
         */

        /**
         * 5 TIME-WAITING 限时等待，调用以下方法时，线程会进入该状态，时间到后，线程会被唤醒，重新竞争锁
         *
         * Thread.sleep(long)
         * Object.wait(long)
         * Thread.join(long)
         * LockSupport.parkNanos()
         * LockSupport.parkUntil()
         */

        /**
         * 6 线程执行完成后，进入结束状态 TERMINATED
         */
        thread2.join();
        System.out.println(thread2.getState());


    }
}
