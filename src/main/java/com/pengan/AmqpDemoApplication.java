package com.pengan;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 	1、RabbitAutoConfiguration
 * 	2、自动配置了连接工厂ConnectionFactory
 * 	3、RabbitProperties封装了RabbitMQ的配置
 * 	4、AmqAdmin:系统的管理功能组件
 */
@EnableRabbit  //开启基于注解的RabbitMQ模式
@SpringBootApplication
public class AmqpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpDemoApplication.class, args);
	}

}
