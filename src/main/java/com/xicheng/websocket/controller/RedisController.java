package com.xicheng.websocket.controller;


import com.xicheng.websocket.entity.RedisThread;
import com.xicheng.websocket.util.DebugIdUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Controller
public class RedisController {

    private static final ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
    private ScheduledFuture scheduledFuture;
    private static final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping("/read")
    @ResponseBody
    public Object redisRead(String d) {
        if (DebugIdUtil.ID_MAP.get(d) == null) {
            DebugIdUtil.ID_MAP.put(d, d);
            scheduledFuture = service.scheduleAtFixedRate(new RedisThread(d, simpMessagingTemplate), 1, 1, TimeUnit.SECONDS);
            DebugIdUtil.TASK_MAP.put(d, scheduledFuture);
        }
        if ("2".equals(d)) {
            System.out.println("移除成功");
            DebugIdUtil.ID_MAP.remove("1");
            ScheduledFuture future = DebugIdUtil.TASK_MAP.get("1");
            future.cancel(true);
        }
        return DebugIdUtil.ID_MAP;
    }

}
