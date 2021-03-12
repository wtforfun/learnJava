package org.learn.lockdemo;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  生产者和消费者的问题
 *  wait、notify/notifyAll() 实现
 */
public class Storage1 implements AbstractStorage {
    //仓库最大容量
    private final int MAX_SIZE = 100;
    //仓库存储的载体
    private LinkedList list = new LinkedList();

    private ReentrantLock lock = new ReentrantLock();

    //生产产品
    public void produce(int num){
        //同步
        try {
            //仓库剩余的容量不足以存放即将要生产的数量，暂停生产
            while (true){
                lock.lock();
                if(list.size()+num > MAX_SIZE){
                    System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:"
                            + list.size() + "\t暂时不能执行生产任务!");
                    lock.unlock();
                }else {
                    break;
                }

                try {
                    Thread.sleep(500);
                }catch (Exception e){

                }
            }


            for(int i=0;i<num;i++){
                list.add(new Object());
            }

            System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());
            lock.unlock();
        } catch (Exception e){

        }
    }

    //消费产品
    public void consume(int num,String cname){

        while (true){
            lock.lock();
            //不满足消费条件
            if(num > list.size()){
                System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能消费生产任务!");
                lock.unlock();
            }else {
                break;
            }

            try {
                Thread.sleep(100);
            }catch (Exception e){

            }
        }




        //消费条件满足，开始消费
        for(int i=0;i<num;i++){
            list.remove();
        }

        System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());
        lock.unlock();
    }
}
