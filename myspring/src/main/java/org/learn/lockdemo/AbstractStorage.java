package org.learn.lockdemo;

public interface AbstractStorage {
    void consume(int num, String cname);
    void produce(int num);
}