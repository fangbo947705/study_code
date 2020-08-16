package com.andrew.study.service.impl;

import com.andrew.study.model.User;
import com.andrew.study.repository.UserRepository;
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
    private UserRepository userRepository;

    @Override
    public User selectById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateById(User user) {
        return userRepository.save(user);
    }
}
