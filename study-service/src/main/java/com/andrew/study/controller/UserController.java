package com.andrew.study.controller;

import com.andrew.study.model.User;
import com.andrew.study.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author bo.fang
 * @Description
 * @Date 1:03 下午 2020/8/2
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/{id}")
    public Object selectById(@PathVariable("id") Long id) {
        return userService.selectById(id);
    }

    @PostMapping("/save")
    public Object updateById(@RequestBody User user) {
        return userService.updateById(user);
    }

}
