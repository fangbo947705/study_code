package com.andrew.common.service;

import java.util.function.Supplier;

/**
 * @Author bo.fang
 * @Description
 * @Date 9:10 下午 2020/9/1
 */
public interface IRedisService {
    /**
     * redis 分布式锁
     *
     * @param key
     * @param value
     * @return
     */
    boolean lock(String key, String value);

    /**
     * redis 分布式锁
     *
     * @param key
     * @param value
     * @param maxTryLockTime
     * @param lockTime
     * @return
     */
    boolean lock(String key, String value, long maxTryLockTime, long lockTime);

    /**
     * 释放锁
     *
     * @param key
     * @param value
     * @return
     */
    boolean unLock(String key, String value);

    /**
     * check redis cache
     * if null query and set value
     * if exist return
     *
     * @param prefixKey
     * @param clazz
     * @param methodName
     * @param expireTime
     * @param param
     * @return
     */
    Object checkAndAddRedisCache(String prefixKey, Class<?> clazz, String methodName, Long expireTime, Object... param);

    /**
     * check redis cache
     * if null query and set value
     * if exist return
     *
     * @param prefixKey
     * @param supplier
     * @param expireTime
     * @param params
     * @return
     */
    <T> T checkAndAddRedisCache(String prefixKey, Supplier<T> supplier, Long expireTime, Object... params);
}
