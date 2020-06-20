package com.andrew.study;

import com.andrew.study.utils.redis.RedisLockUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class StudyApplicationTests {

    @Resource
    private RedisLockUtil redisLockUtil;

    @Test
    void contextLoads() {
    }

    @Test
    public void redisLockTest() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(redisLockUtil.lock("me", uuid));

        System.out.println(redisLockUtil.unLock("me", uuid));

        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {

            int finalI = i;
            threads[i] = new Thread(() -> {
                redisLockUtil.lock(String.valueOf(finalI), String.valueOf(finalI));
                redisLockUtil.unLock(String.valueOf(finalI), String.valueOf(finalI));
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
