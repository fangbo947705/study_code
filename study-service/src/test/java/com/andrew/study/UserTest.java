package com.andrew.study;

import com.andrew.study.constant.MdcConstant;
import com.andrew.study.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author bo.fang
 * @Description
 * @Date 1:07 下午 2020/8/2
 */
@SpringBootTest
@Slf4j
public class UserTest {

    @Resource
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        log.info("开始测试-{}", System.currentTimeMillis());
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    public void after() {
        log.info("测试结束-{}", System.currentTimeMillis());
    }

    @Test
    public void selectById() throws Exception {
        log.info("selectById测试中，{}", System.currentTimeMillis());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 12)
                .header(MdcConstant.TRANCE_ID, UUID.randomUUID().toString().replaceAll("-", "")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info("请求结果:{}", mvcResult.getResponse().getContentAsString());
    }

}
