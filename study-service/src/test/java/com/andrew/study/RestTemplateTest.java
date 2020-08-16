package com.andrew.study;

import com.andrew.study.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletionService;

/**
 * @Author bo.fang
 * @Description
 * @Date 6:45 下午 2020/8/2
 */
@Slf4j
@SpringBootTest
public class RestTemplateTest {

    @Resource
    private HttpClient httpClient;

    @Resource
    private RestTemplate restTemplate;


    @Test
    public void testHttpClient() throws JsonProcessingException {
        String result = restTemplate.getForObject("http://127.0.0.1:8081/user/{id}", String.class, 12);
        log.info("请求结果:{}", result);
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(result, User.class);
        log.info("user:{}", user);

        User resultUser = restTemplate.getForObject("http://127.0.0.1:8081/user/{id}", User.class, 12);
        log.info("user-2:{}", resultUser);
    }

    @Test
    public void updateById() throws JsonProcessingException {
        String str = "{\"id\":12,\"name\":\"andrew.fang\",\"age\":30,\"email\":null}";
        User user = new ObjectMapper().readValue(str, User.class);
        user.setEmail("andrew.fang@qq.com");
//        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
//        multiValueMap.put("traceId", Collections.singletonList(UUID.randomUUID().toString().replaceAll("-", "")));
//        HttpEntity httpEntity = new HttpEntity(user, multiValueMap);
        String result = restTemplate.postForObject("http://127.0.0.1:8081/user/save", user, String.class);
        log.info("结果：{}", result);
    }
}
