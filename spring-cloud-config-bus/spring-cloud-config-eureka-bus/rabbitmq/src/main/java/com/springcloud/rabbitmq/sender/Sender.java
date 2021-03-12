package com.springcloud.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
/**
 * 消息生产者
 * */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //send方法
    public void send() throws Exception{
        String str = "hello " + new Date();
        System.out.println("Sender : " + str);
        //将消息发送到名为hello的队列
        this.amqpTemplate.convertAndSend("hello", str);
    }
}
