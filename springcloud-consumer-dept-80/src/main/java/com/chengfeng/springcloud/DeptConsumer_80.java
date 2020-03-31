package com.chengfeng.springcloud;

import com.chengfeng.myrule.ChengFengRandomRule;
import com.chengfeng.myrule.ChengFengRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//在微服务启动的时候就能去加载我们自定义的ribbon类
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = ChengFengRandomRule.class)
//@RibbonClient
public class DeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class, args);
    }
}
