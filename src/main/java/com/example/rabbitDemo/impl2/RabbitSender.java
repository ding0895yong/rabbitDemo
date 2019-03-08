package com.example.rabbitDemo.impl2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
public class RabbitSender {
 
    @Autowired
    private RabbitTemplate rabbitTemplate;
 
    // 发送消息方法调用: 构建Message消息
    public void send(Order order) throws Exception {
        // 消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getId());
        rabbitTemplate.convertAndSend("order-exchange", // exchange：交换机
                "order.abcd", // routing-key
                            order, // 消息体内容
                            correlationData); // 消息唯一ID
    }
}
