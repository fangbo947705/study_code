package com.andrew.common.util;

import java.util.UUID;

/**
 * @Author bo.fang
 * @Description
 * @Date 4:25 下午 2020/8/16
 */
public class UuidUtil {

    /**
     * 生成uuid 去掉-
     *
     * @return
     */
    public static String createUuidWithout() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
