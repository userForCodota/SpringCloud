package com.chengfeng.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    //这里的@Value("${}")意思就是从yml配置文件里面读信息，然后这里是config服务端3355，是去读远程git上面的配置，所以就可
    //以实现了远程读取git配置
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;
    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public String getConfig() {
        return "applicationName=>" + applicationName + "\t\t;" +
                "eurekaServer=>" + eurekaServer + "\t\t;" +
                "port=>" + port + "\t\t;"
                ;
    }

}
