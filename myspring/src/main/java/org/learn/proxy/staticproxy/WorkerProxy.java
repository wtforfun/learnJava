package org.learn.proxy.staticproxy;

public class WorkerProxy implements IWork{

    private Worker worker;

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void work() {
        System.out.println("proxy is looking..");
        worker.work();
    }
}
