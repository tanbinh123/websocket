package com.xicheng.websocket.util;

import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

public class DebugIdUtil {

    public static final HashMap<String, String> ID_MAP = new HashMap<>();
    public static final HashMap<String, ScheduledFuture> TASK_MAP = new HashMap<>();
}
