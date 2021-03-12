package org.learn.springaop.aspectj;

public class AspectWorker {

    public void sayHello(){
        System.out.println("hello world !");
    }
    public static void main(String args[]){
        AspectWorker helloWord =new AspectWorker();
        helloWord.sayHello();
    }

}
