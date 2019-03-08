package com.example.rabbitDemo.impl2;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
* 接收消息
*/
@Component
public class OrderReceiver {

   /**
    * 交换机、队列不存在的话，以下注解可以自动创建交换机和队列
    *
    * @param order
    * @param headers
    * @param channel
    * @throws Exception
    */
   @RabbitListener(bindings = @QueueBinding(
           value = @Queue(value = "order-queue", durable = "true"),
           exchange = @Exchange(value = "order-exchange", durable = "true", type = "topic"),
           key = "order.#"
   ))

   /**
    * 消费者接收消息并消费消息
    *
    * @param order
    * @param headers
    * @param channel
    * @throws Exception
    */
   @RabbitHandler
   public void onOrderMessage(@Payload Order order,
                              @Headers Map<String, Object> headers,
                              Channel channel) throws Exception {
       System.out.println("--------------收到消息，开始消费------------");
       System.out.println("订单ID是：" + order.getId());
       Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
       // ACK
       Thread.sleep(500);
       channel.basicAck(deliveryTag, false);
   }
}
