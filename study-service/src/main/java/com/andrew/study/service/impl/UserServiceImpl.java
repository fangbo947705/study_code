package com.andrew.study.service.impl;

import com.andrew.study.mapper.UserMapper;
import com.andrew.study.model.User;
import com.andrew.study.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author bo.fang
 * @Description
 * @Date 1:01 下午 2020/8/2
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(User user) {
        return userMapper.updateById(user);
    }
}
