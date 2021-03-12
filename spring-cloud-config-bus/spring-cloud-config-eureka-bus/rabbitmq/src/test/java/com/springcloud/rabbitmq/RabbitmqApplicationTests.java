package com.springcloud.rabbitmq;

import com.springcloud.rabbitmq.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	Sender sender;

	@Test
	public void sendMs() throws Exception{
		sender.send();
	}
}
