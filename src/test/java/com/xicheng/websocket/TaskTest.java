package com.xicheng.websocket;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskTest {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 32, 15, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000));

    @Test
    public void testTask() {
        ScheduledFuture<?> start = new ThreadPoolTaskScheduler().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
            }
        }, 1000);

    }


}
