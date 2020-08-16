package com.andrew.study.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author bo.fang
 * @Description
 * @Date 9:51 上午 2020/8/2
 */
@Data
@TableName("t_user")
public class User {

    @TableId
    private Long id;

    private String name;

    private Integer age;

    private String email;

}
