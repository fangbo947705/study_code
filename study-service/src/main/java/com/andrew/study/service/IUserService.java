package com.andrew.study.service;

import com.andrew.study.model.User;

/**
 * @Author bo.fang
 * @Description
 * @Date 1:01 下午 2020/8/2
 */
public interface IUserService {

    /**
     *
     * @param id
     * @return
     */
    User selectById(Long id);

    /**
     *
     * @param user
     * @return
     */
    int updateById(User user);
}
