package org.learn.synchronizedemo;

public interface AbstractStorage {
    void consume(int num,String cname);
    void produce(int num);
}