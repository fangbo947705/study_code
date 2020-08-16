package com.andrew.study.controller;

import com.andrew.study.feign.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author bo.fang
 * @Description
 * @Date 12:35 上午 2020/8/4
 */
@RestController
@RequestMapping("/fegin/user")
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object selectById(@PathVariable("id") Long id) {
        return userService.selectById(id);
    }

    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/rest/{id}")
    public Object selectByIdUserRestTemplate(@PathVariable("id") Long id) {
        return restTemplate.getForObject("http://study/study/user/{id}", Object.class, id);
    }
}
