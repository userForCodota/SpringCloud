package com.chengfeng.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//将这个类作为主启动类
@SpringBootApplication
@EnableEurekaClient //注解EnableEurekaClient表示此app在服务启动后自动注册到eureka中
@EnableDiscoveryClient //开启服务发现（扩展一下）
public class DeptProvider_8002 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8002.class, args);
    }
}
