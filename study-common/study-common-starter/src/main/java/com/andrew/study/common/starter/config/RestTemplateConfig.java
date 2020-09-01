package com.andrew.study.common.starter.config;

import com.andrew.study.common.starter.interceptor.RestTemplateInterceptor;
import com.andrew.common.properties.RestTemplateProperties;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @Author bo.fang
 * @Description
 * @Date 6:28 下午 2020/8/2
 */
@Configuration
@EnableConfigurationProperties(RestTemplateProperties.class)
public class RestTemplateConfig {

    @Bean(name = "customRestTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
                new PoolingHttpClientConnectionManager(registry);
        RestTemplateProperties restTemplateProperties = new RestTemplateProperties();
        poolingHttpClientConnectionManager.setMaxTotal(restTemplateProperties.getMaxTotal());
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(restTemplateProperties.getDefaultMaxPerRoute());
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(restTemplateProperties.getSocketTimeout())
                .setConnectTimeout(restTemplateProperties.getConnectTimeout())
                .setConnectionRequestTimeout(restTemplateProperties.getConnectionRequestTimeout())
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();
    }
}
