package com.pengan;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class AmqpDemoApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //管理rabbit后台的对象
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    void contextLoads() {
        //自己定义MessageProperties
//		MessageProperties messageProperties = new MessageProperties();
//		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
//		Message message = new Message("hello,i'm spring boot".getBytes(), messageProperties);
//		rabbitTemplate.send("pengan.direct","direct.news",message);

        //object默认当作消息体,会将对象序列化发送出去
        rabbitTemplate.convertAndSend("pengan.direct", "direct.news", "ni hao,I'm spring boot");
    }

    /**
     *发送简单消息，只要指定一个队列名即可
     */
    @Test
    void simplestSend() {
        rabbitTemplate.convertAndSend("simplest.queue","from spring simplestSend method");
    }

    @Test
    void receive() {
        //
        //Message receive = rabbitTemplate.receive("direct.news");
        //System.out.println(receive.getBody().toString());

        //接收并自动转换
        Object o = rabbitTemplate.receiveAndConvert("direct.news");
        System.out.println(o);
    }

    @Test
    void sendJsonData() {
        //使用MyAMQPConfig配置成json的转换器
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("one", "nihao");
        stringStringHashMap.put("two", "你好");
        rabbitTemplate.convertAndSend("pengan.fanout", "", stringStringHashMap);
    }

    @Test
    void receiveJsonData() {
        Object o = rabbitTemplate.receiveAndConvert("fanout.new");
        System.out.println(o);
    }

    @Test
	void amqpAdminTest() {
		//声明一个Exchange
		amqpAdmin.declareExchange(new DirectExchange("springbootExchange.direct"));
		//声明一个队列
		amqpAdmin.declareQueue(new Queue("springboot.queue", true));
		//将queue绑定Exchange
		amqpAdmin.declareBinding(new Binding("springboot.queue",
				Binding.DestinationType.QUEUE,
				"springbootExchange.direct",
				"springboot.queue",null));
	}
}
