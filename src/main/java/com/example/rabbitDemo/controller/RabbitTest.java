package com.example.rabbitDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitDemo.impl.HelloReceiver2;
import com.example.rabbitDemo.impl.HelloSender1;
import com.example.rabbitDemo.impl2.Order;
import com.example.rabbitDemo.impl2.RabbitSender;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {
    
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloReceiver2 helloSender2;
    @Autowired
    private RabbitSender rabbitSender;
    
    @PostMapping("/hello")
    public void hello() {
        helloSender1.send("sss");
    }
    
    /**
     * 单生产者-多消费者
     */
    @PostMapping("/oneToMany")
    public void oneToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("hellomsg:"+i);
        }
        
    }
    @PostMapping("/send")
    public void send() {
    	for(int i=0;i<1000;i++){
    		Order order = new Order();
    		order.setId("message"+i);
    		try {
				rabbitSender.send(order);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
}
