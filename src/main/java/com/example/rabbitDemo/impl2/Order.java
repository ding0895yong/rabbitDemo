package com.example.rabbitDemo.impl2;

import java.io.Serializable;

public class Order implements Serializable {
 
    private static final long serialVersionUID = 7589820506567799193L;
 
    private String id;
 
    private String name;
 
    /**
     * 消息ID
     */
    private String messageId;
 
    public Order() {
 
    }
 
    public Order(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getMessageId() {
        return messageId;
    }
 
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
