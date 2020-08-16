package com.andrew.study.repository;

import com.andrew.study.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author bo.fang
 * @Description
 * @Date 12:27 下午 2020/8/15
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
