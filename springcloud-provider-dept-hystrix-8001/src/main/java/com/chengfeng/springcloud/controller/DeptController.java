package com.chengfeng.springcloud.controller;


import com.chengfeng.springcloud.pojo.Dept;
import com.chengfeng.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//提供Restful服务
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if (dept == null) {
            throw new RuntimeException("id=>" + id + "，不存在该用户，或者信息无法找到");
        }
        return dept;
    }

    //将上面的方法改造成Hystrix的熔断机制版
    public Dept hystrixGet(@PathVariable("id") Long id) {
//        //这里为什么链式写法报错？检查后发现IDEA2019.1需要按照lombok插件
        return new Dept()
                .setDeptno(id)
                .setDname("id=>" + id + "，不存在该用户，或者信息无法找到")
                .setDb_source("no this database in mysql");
    }
}
