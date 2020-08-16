package com.andrew.study.service;

import com.andrew.study.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author bo.fang
 * @Description
 * @Date 1:01 下午 2020/8/2
 */
@FeignClient("service-2")
public interface IUserService {

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "/service-2/user/{id}")
    User selectById(@PathVariable("id") Long id);

    /**
     * @param user
     * @return
     */
    @PostMapping("/service-2/user/save")
    User updateById(User user);
}
