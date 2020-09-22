package com.andrew.study;

import com.andrew.common.service.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author bo.fang
 * @Description
 * @Date 10:22 下午 2020/9/10
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Service2ApplicationTests {

    @Resource
    private IRedisService redisLockService;

    @Test
    public void test(){
        String key = "test";
        redisLockService.lock(key,key,5L,10);
        redisLockService.lock(key,key,5L,10);
        redisLockService.lock("aaa","aaa",5L,10);
    }
}
