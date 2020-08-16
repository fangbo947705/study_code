package com.andrew.study.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author bo.fang
 * @Description
 * @Date 12:33 上午 2020/8/4
 */
@FeignClient("study")
@RequestMapping("user")
public interface IUserService {

    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Object selectById(@PathVariable("id") Long id);
}
