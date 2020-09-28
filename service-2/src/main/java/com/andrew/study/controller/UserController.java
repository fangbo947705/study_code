package com.andrew.study.controller;

import com.andrew.common.annotation.GlobalResponseBody;
import com.andrew.common.service.IRedisService;
import com.andrew.common.util.UuidUtil;
import com.andrew.study.model.User;
import com.andrew.study.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author bo.fang
 * @Description
 * @Date 1:03 下午 2020/8/2
 */
@RestController
@RequestMapping("/user")
@RefreshScope
@Slf4j
public class UserController {

    @Value("${name}")
    private String myName;

    @Resource
    private IUserService userService;

    @Resource(name = "customRestTemplate")
    private RestTemplate restTemplate;

    @Resource
    private IRedisService redisLockService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/{id}")
    @GlobalResponseBody
    public Object selectById(@PathVariable("id") Long id) {
        log.info(UserController.class.getName() + "selectById,id:{}", id);
//        return redisLockService.checkAndAddRedisCache("service2-", IUserService.class, "selectById", 60000L, id);
        return userService.selectById(id);
    }

    @PostMapping("/save")
    public Object updateById(@RequestBody User user) {
        return userService.updateById(user);
    }

    @GetMapping("/name")
    public Object testNacosConfig() {
        return myName;
    }

    @GetMapping("/testRestTemplate")
    public Object testRestTemplate() {
        return restTemplate.getForObject("http://127.0.0.1:8083/user/12", String.class);
    }

    @GetMapping("/testRedisLock")
    public boolean testRedisLock() {
        stringRedisTemplate.opsForValue().set("nihao", UuidUtil.createUuidWithout(), 100, TimeUnit.SECONDS);
        return redisLockService.lock("nihao_lock", UuidUtil.createUuidWithout(), 60L);
    }

}
