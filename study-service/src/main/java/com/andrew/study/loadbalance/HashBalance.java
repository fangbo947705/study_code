package com.andrew.study.loadbalance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @Dedc 负载均衡--hash算法
 * @Author bo.fang
 * @Description
 * @Date 3:45 下午 2020/6/20
 */
public class HashBalance {

    private static final Map<String, String> SERVER_MAP = new ConcurrentHashMap<>();

    public static final List<String> SERVICES = new ArrayList<>();

    static {
        SERVER_MAP.put("server1", "192.168.1.1");
        SERVER_MAP.put("server2", "192.168.1.2");
        SERVER_MAP.put("server3", "192.168.1.3");
        SERVICES.addAll(SERVER_MAP.keySet());
        Collections.sort(SERVICES);
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).parallel().forEach(s -> {
            String ip = "192.168.1." + s;
            String server = hashBalance(ip);
            System.out.println(String.format("客户端ip:%s,服务器名：%s,地址：%s", ip, server, SERVER_MAP.get(server)));
        });
    }

    public static String hashBalance(String ip) {
        int index = ip.hashCode() % SERVICES.size();
        return SERVICES.get(index);
    }
}
