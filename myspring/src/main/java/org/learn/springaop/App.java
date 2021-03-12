package org.learn.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // 加载Spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-mvc.xml");
        AopWorker worker = applicationContext.getBean(AopWorker.class);
        worker.work();

    }
}
