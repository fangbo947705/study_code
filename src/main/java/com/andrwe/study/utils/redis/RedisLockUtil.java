package com.andrwe.study.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @Author bo.fang
 * @Description
 * @Date 11:56 下午 2020/6/13
 */
@Component
public class RedisLockUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockUtil.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String REDIS_KEY_PREFIX = "REDIS_LOCK_";

    private static final Long UNLOCK_SUCCESS = 1L;


    /**
     * 获取锁超时时间
     */
    private static final long MAX_LOCK_TIME_OUT = 6000;

    private static final String UNLOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] " +
            "    then " +
            "    return redis.call('del', KEYS[1]) " +
            "else " +
            "   return 0 " +
            "end";

    /**
     * redis 分布式锁
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key, String value) {
        String lockKey = REDIS_KEY_PREFIX + key;
        long startTime = System.currentTimeMillis();
        LOGGER.info("获取redis锁{},时间：{}", lockKey, LocalDateTime.now());
        while (true) {
            if (System.currentTimeMillis() - startTime > MAX_LOCK_TIME_OUT) {
                LOGGER.info("获取redis锁{}超时,时间：{}", lockKey, LocalDateTime.now());
                return false;
            }
            boolean result = stringRedisTemplate.opsForValue()
                    .setIfAbsent(lockKey, value, 50, TimeUnit.SECONDS);
            if (result) {
                LOGGER.info("获取redis锁{}成功,时间：{}", lockKey, LocalDateTime.now());
                return true;
            }
        }
    }

    /**
     * 释放redis 分布式锁
     *
     * @param key
     * @param value
     */
    public boolean unLock(String key, String value) {
        RedisScript<Long> redisScript = new DefaultRedisScript<>(UNLOCK_LUA_SCRIPT, Long.class);
        Long result = stringRedisTemplate.execute(redisScript, Collections.singletonList(REDIS_KEY_PREFIX + key), value);
        return UNLOCK_SUCCESS.equals(result);
    }

}
