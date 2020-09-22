package com.andrew.common.service.impl;

import com.andrew.common.constant.BaseConstant;
import com.andrew.common.properties.RedisLockProperties;
import com.andrew.common.service.IRedisService;
import com.andrew.common.spring.SpringContextHolder;
import com.andrew.common.util.MD5Util;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author bo.fang
 * @Description
 * @Date 9:13 下午 2020/9/1
 */
@Slf4j
@Component
public class RedisServiceImpl implements IRedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedisLockProperties redisLockProperties;

    private static final String UNLOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] " +
            "    then " +
            "    return redis.call('del', KEYS[1]) " +
            "else " +
            "   return 0 " +
            "end";

    @Override
    public boolean lock(String key, String value) {
        return lock(key, value, redisLockProperties.getMaxTryLockTime(), redisLockProperties.getLockTime());
    }

    @Override
    public boolean lock(String key, String value, long maxTryLockTime, long lockTime) {
        String lockKey = redisLockProperties.getRedisLockKeyPrefix() + key;
        long startTime = System.currentTimeMillis();
        log.info("get redis distributed lock{},time：{}", lockKey, LocalDateTime.now());
        while (true) {
            if (System.currentTimeMillis() - startTime > maxTryLockTime) {
                log.info("get redis Distributed lock {} timeout:{},时间：{}", lockKey, maxTryLockTime,
                        LocalDateTime.now());
                return false;
            }
            boolean result = stringRedisTemplate.opsForValue()
                    .setIfAbsent(lockKey, value, lockTime, TimeUnit.SECONDS);
            if (result) {
                log.info("get redis Distributed lock {} success,time：{}", lockKey, LocalDateTime.now());
                return true;
            }

        }
    }


    @Override
    public boolean unLock(String key, String value) {
        RedisScript<Long> redisScript = new DefaultRedisScript<>(UNLOCK_LUA_SCRIPT, Long.class);
        Long result = stringRedisTemplate.execute(redisScript,
                Collections.singletonList(redisLockProperties.getRedisLockKeyPrefix() + key), value);
        return BaseConstant.UNLOCK_SUCCESS.equals(result);
    }

    @Override
    public Object checkAndAddRedisCache(String prefixKey, Class<?> clazz, String methodName, Long expireTime,
                                        Object... params) {
        log.info("check and add redis cache ,prefixKey:{},class:{},methodName:{},expireName:{},params:{}",
                prefixKey, clazz, methodName, expireTime, params);
        Map<String, Object> keyMap = generatorCacheKey(prefixKey, params);
        String cacheKey = (String) keyMap.get("key");
        Boolean flag = (Boolean) keyMap.get("flag");
        log.info("check and add redis cache key:{},flag:{}", cacheKey, flag);
        if (flag) {
            Object result = redisTemplate.opsForValue().get(cacheKey);
            if (Objects.nonNull(result)) {
                return result;
            }
            result = invokeResult(clazz, methodName, params);
            if (Objects.isNull(result)) {
                return null;
            }
            redisTemplate.opsForValue().set(cacheKey, result, getExpireTime(expireTime), TimeUnit.SECONDS);
            return result;
        }
        return invokeResult(clazz, methodName, params);
    }

    @Override
    public <T> T checkAndAddRedisCache(String prefixKey, Supplier<T> supplier, Long expireTime, Object... params) {
        log.info("check and add redis cache ,prefixKey:{},expireName:{},params:{}",
                prefixKey, expireTime, params);
        Map<String, Object> keyMap = generatorCacheKey(prefixKey, params);
        String cacheKey = (String) keyMap.get("key");
        Boolean flag = (Boolean) keyMap.get("flag");
        log.info("check and add redis cache key:{},flag:{}", cacheKey, flag);
        if (flag) {
            T result = (T) redisTemplate.opsForValue().get(cacheKey);
            if (Objects.nonNull(result)) {
                return result;
            }
            result = supplier.get();
            if (Objects.isNull(result)) {
                return null;
            }
            redisTemplate.opsForValue().set(cacheKey, result, getExpireTime(expireTime), TimeUnit.SECONDS);
            return result;
        }
        return supplier.get();
    }


    /**
     * 获取过期时间
     *
     * @param expireTime
     * @return
     */
    private long getExpireTime(Long expireTime) {
        if (Objects.isNull(expireTime)) {
            return redisLockProperties.getRedisCacheDefaultExpireTime();
        }
        return expireTime;
    }

    /**
     * @param clazz
     * @param methodName
     * @param params
     * @return
     */
    private Object invokeResult(Class<?> clazz, String methodName, Object... params) {
        Method method = Arrays.stream(clazz.getDeclaredMethods())
                .filter(mt -> methodName.equalsIgnoreCase(mt.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("haven't find this method :%s on " +
                        "class:%s", methodName, clazz)));
        Object objectBean = SpringContextHolder.getBean(clazz);
        try {
            return method.invoke(objectBean, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("reflect invoke method error:{}", e.getMessage(), e);
            throw new RuntimeException("reflect invoke method error" + e.getMessage());
        }
    }

    /**
     * 根据参数构造缓存key
     *
     * @param prefixKey
     * @param params
     * @return
     */
    private Map<String, Object> generatorCacheKey(String prefixKey, Object... params) {
        boolean flag = true;
        Map<String, Object> map = new HashMap<>(8);
        StringBuilder sb = new StringBuilder();

        if (Objects.isNull(params) || params.length == 0) {
            flag = false;
        } else {
            Gson gson = new Gson();
            Arrays.stream(params)
                    .forEach(param -> sb.append(gson.toJson(param)));
        }
        String key = prefixKey + "-" + MD5Util.getMD5(sb.toString());
        map.put("flag", flag);
        map.put("key", key);
        return map;
    }
}
