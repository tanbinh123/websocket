package com.xicheng.websocket.entity;


import com.xicheng.websocket.util.DebugIdUtil;
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
        if (!DebugIdUtil.ID_MAP.containsKey(this.threadName)) {
            System.out.println("stop--------------");
            this.stop();
        }
        this.simpMessagingTemplate.convertAndSend("/topic/test", threadName);
        this.setName(threadName);
        // if (!DebugIdUtil.ID_MAP.containsKey(this.threadName)) {

    }
}
