package com.xicheng.websocket.entity;


import org.springframework.messaging.simp.SimpMessagingTemplate;

public class RedisThread extends Thread {
    private String threadName;

    private SimpMessagingTemplate simpMessagingTemplate;

    public RedisThread() {
    }

    public RedisThread(String threadName) {
        this.threadName = threadName;
    }

    public RedisThread(String threadName, SimpMessagingTemplate simpMessagingTemplate) {
        this.threadName = threadName;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public RedisThread(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void run() {

        this.setName(threadName);
        this.simpMessagingTemplate.convertAndSend("/topic/test" + threadName, threadName);
    }
}
