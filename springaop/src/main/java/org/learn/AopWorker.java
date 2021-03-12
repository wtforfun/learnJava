package org.learn;

public class AopWorker {

    public void work(){
        System.out.println("work");
    }

    @MyAspectAnno(isStart = true)
    public void rest(){
        System.out.println("rest");
    }
}
