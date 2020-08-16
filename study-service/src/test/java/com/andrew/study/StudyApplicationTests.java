package com.andrew.study;

import com.andrew.study.mapper.UserMapper;
import com.andrew.study.model.User;
import com.andrew.study.utils.redis.RedisLockUtil;
import com.google.common.util.concurrent.Futures;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
@MapperScan(basePackages = "com.andrew.study.mapper")
class StudyApplicationTests {

    @Resource
    private RedisLockUtil redisLockUtil;

    @Resource
    private UserMapper userMapper;

    @Resource(name = "asyncTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    void contextLoads() {
    }

    @Test
    public void redisLockTest() {
        threadPoolTaskExecutor.execute(() -> {
            log.info("线程名：{},{}", Thread.currentThread().getName(), threadPoolTaskExecutor.getActiveCount());
        });

    }

    @Test
    public void testFuture(){

    }

    @Test
    public void testMybatisPlusSelect(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }
}
