package com.andrew.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author bo.fang
 * @Description
 * @Date 11:04 下午 2020/8/16
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "redis.lock")
public class RedisLockProperties {

    /**
     * redis lock key prefix
     */
    private String redisLockKeyPrefix = "REDIS_LOCK_";

    /**
     * key lock time
     */
    private long lockTime = 50L;

    /**
     * check and set redis cache default expireTime
     */
    private Long redisCacheDefaultExpireTime = 1800L;
}
