package com.chengfeng.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//@Configuration就相当于spring的applicationContext.xml
public class configBean {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
