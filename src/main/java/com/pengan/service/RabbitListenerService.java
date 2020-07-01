package com.pengan.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RabbitListenerService {

    //当配置了转换器后，方法形参就要是相对应类型的参数（比如该项目中配置了jackson为转换器，那么形参就不能是字符串类型，必须是相对应的类型）
//    @RabbitListener(queues = {"news"})
//    public void receiveNewQueues(Map map){
//        System.out.println(map);
//    }

    @RabbitListener(queues = {"news"})
    public void receiveNewQueuesMessage(Message message){
        System.out.println("message:");
        //进行强转
        System.out.println(message.getBody());
    }

}
