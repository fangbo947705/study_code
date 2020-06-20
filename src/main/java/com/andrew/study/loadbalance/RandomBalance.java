package com.andrew.study.loadbalance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @Dedc 负载均衡--随机算法
 * @Author bo.fang
 * @Description
 * @Date 3:45 下午 2020/6/20
 */
public class RandomBalance {

    private static final Map<String, String> SERVER_MAP = new ConcurrentHashMap<>();

    public static final List<String> SERVICES = new ArrayList<>();

    public static final Random RANDOM = new Random();

    static {
        SERVER_MAP.put("server1", "192.168.1.1");
        SERVER_MAP.put("server2", "192.168.1.2");
        SERVER_MAP.put("server3", "192.168.1.3");
        SERVICES.addAll(SERVER_MAP.keySet());
        Collections.sort(SERVICES);
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).parallel().forEach(s -> {
            String server = randomBalance();
            System.out.println(String.format("服务器名：%s,地址：%s", server, SERVER_MAP.get(server)));
        });
    }

    public static String randomBalance() {
        int size = SERVICES.size();
        int index = RANDOM.nextInt(size);
        return SERVICES.get(index);
    }
}
