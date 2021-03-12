package org.channel.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @EnableConfigServer注解标识此处是config的server端。
 * @EnableDiscoveryClient eureka注册中心客服端标识 @EnableEurekaClient同样的效果
 *
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"org.channel.config", "org.channel.common"})
public class ConfigServerApplication
{
    // 配置中心
    public static void main(String[] args)
    {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
