package com.andrwe.study.utils.bean;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author bo.fang
 * @Description
 * @Date 11:46 上午 2020/6/7
 */
public class BeanCopierUtils {

    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new HashMap<>();

    public static BeanCopier beanCopier(Class<?> source, Class<?> target, boolean useConverter) {
        String key = source.getName() + target.getName() + (useConverter ? "Y" : "N");
        BeanCopier beanCopier;
        if (BEAN_COPIER_CACHE.containsKey(key)) {
            beanCopier = BEAN_COPIER_CACHE.get(key);
        } else {
            beanCopier = BeanCopier.create(source, target, useConverter);
            BEAN_COPIER_CACHE.putIfAbsent(key, beanCopier);
        }
        if (Objects.isNull(beanCopier)) {
            throw new RuntimeException(String.format("未获取到source：[%s],target:[%s],useConverter:[%s],对应的beanCopier",
                    source.getName(), target.getName(), useConverter));
        }
        return beanCopier;
    }

    public static void copy(Class<?> source, Class<?> target, boolean useConverter, Object src, Object dest,
                            Converter converter) {
        BeanCopier beanCopier = beanCopier(source, target, useConverter);
        beanCopier.copy(src, dest, converter);
    }

    public static void main(String[] args) throws InterruptedException {

    }

}
