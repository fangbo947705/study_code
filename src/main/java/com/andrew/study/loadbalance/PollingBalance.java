package com.andrew.study.loadbalance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @Author bo.fang
 * @Description 负载均衡--轮询算法
 * @Date 8:49 下午 2020/6/18
 */
public class PollingBalance {
    private static final Map<String, String> SERVER_MAP = new ConcurrentHashMap<>();

    public static Integer num = 0;

    public static final List<String> SERVICES = new ArrayList<>();

    static {
        SERVER_MAP.put("server1", "192.168.1.1");
        SERVER_MAP.put("server2", "192.168.1.2");
        SERVER_MAP.put("server3", "192.168.1.3");
        SERVICES.addAll(SERVER_MAP.keySet());
        Collections.sort(SERVICES);
    }

    public static void main(String[] args) {

        IntStream.range(0, 100).parallel()
                .forEach(s -> {
                    String server = pollingBalance();
                    System.out.println(String.format("服务器名：%s,地址：%s", server, SERVER_MAP.get(server)));
                });
    }

    public static String pollingBalance() {
        String service = "";
        synchronized (num) {
            if (num >= SERVER_MAP.size()) {
                num = 0;
            }
            service = SERVICES.get(num);
            num++;
        }
        return service;
    }

}
