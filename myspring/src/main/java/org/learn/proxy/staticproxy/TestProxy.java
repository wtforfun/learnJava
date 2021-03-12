package org.learn.proxy.staticproxy;

public class TestProxy {

    public static void main(String[] args) {
        Worker worker = new Worker();
        WorkerProxy proxy = new WorkerProxy();
        proxy.setWorker(worker);
        proxy.work();
    }
}
