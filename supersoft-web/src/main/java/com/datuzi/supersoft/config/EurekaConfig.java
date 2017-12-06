package com.datuzi.supersoft.config;

import com.datuzi.supersoft.interceptor.XUserAgentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author zhangjianbo
 * @date 2017/5/10
 */
@Configuration
public class EurekaConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.<ClientHttpRequestInterceptor>singletonList(new XUserAgentInterceptor()));
        return restTemplate;
    }
}
