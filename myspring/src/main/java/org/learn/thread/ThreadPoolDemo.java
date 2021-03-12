package org.learn.thread;

import java.util.concurrent.*;

/**
 * ThreadPoolExecutor是线程池的真正实现类
 他通过构造方法的一系列参数，来构成不同配置的线程池。
 常用的构造方法有下面四个
 这些方法都是通过传递不同的参数创建ThreadPoolExecutor对象
 */
public class ThreadPoolDemo {


    /**
     * 固定线程数的线程池（核心线程数和最大线程数相等）
     * return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
     * 队列是一个链表，没有固定长度，理论长度是整型最大值，可以导致队列无限长，最终OOM
     * @return
     */
    public static ExecutorService getFixedThreadPool(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        return fixedThreadPool;
    }

    /**
     * 单一线程的线程池（核心线程数和最大线程数都是1）
     * return new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
     * 队列同样是一个链表，没有固定长度，可能因队列长度无线长，最终OOM
     */
    public static ExecutorService getSingleThreadPool(){
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        return singleThreadPool;
    }

    /**
     *  可缓存线程池
     *  构造方法如下，最大线程数是整型的最大值，可能创建巨量的线程，导致OOM
     *  new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
     * @return
     */
    public static ExecutorService getCachedThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        return cachedThreadPool;
    }


    /**
     * 大小无限的线程池
     * 构造方法 super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue());
     * 最大线程数没有上限，DelayedWorkQueue 是无界队列, 基于数组实现, 队列的长度可以扩容到 Integer.MAX_VALUE。
     * 所以最大线程没有限制，队列长度没有限制，对资源的消耗最大，容易OOM
     * @return
     */
    public static ExecutorService getScheduledThreadPool(){
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        return scheduledThreadPool;
    }


    /***
     * 根据阿里的java开发规范，上面4种方式都不推荐，而是推荐直接使用ThreadPoolExecutor的构造方法自己来构建线程池
     * 推荐的方式如下，直接调用threadPoolExecutor的构造方法，限制最大线程数，限制队列长度，这样不会OOM，但要注意拒绝策略
     *
     * @return
     */
    public static ThreadPoolExecutor getThreadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(4,8,1000, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(100));
        return threadPoolExecutor;
    }

    /**
     * 测试题 下面的代码会输出什么？
     * 输出0，因为核心线程数是1，队列是100，总共5个任务，第一个进入执行后，
     * 其他任务会进入到队列里面，而第一个任务占有核心线程 并且一直sleep，
     * 导致第一个线程一直被占用，而队列没有超，并不会创建新的线程来执行任务
     * 如果队列满了，但线程数没有超过最大线程数，会创建新的线程来执行任务，
     * 但队列里的任务会继续等待核心线程
     * @param args
     */
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, //corePoolSize
                10, //maximumPoolSize
                100, //keepAliveTime
                TimeUnit.SECONDS, //unit
                new LinkedBlockingDeque<>(5),new UserThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());//workQueue

        for (int i = 0; i < 50; i++) {
            final int taskIndex = i;
            executor.execute(() -> {
                System.out.println(taskIndex +" " +Thread.currentThread().getName());

            });
        }



//        executor.shutdown();
    }
}
