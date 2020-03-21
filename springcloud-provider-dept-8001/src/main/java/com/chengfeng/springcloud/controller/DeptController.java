package com.chengfeng.springcloud.controller;


import com.chengfeng.springcloud.pojo.Dept;
import com.chengfeng.springcloud.service.DeptService;
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
    @Autowired
    private DiscoveryClient client; //获取微服务信息

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> quaryAll() {
        return deptService.queryAll();
    }

    //通过eureka中微服务的名称获取其信息
    @GetMapping("/dept/discovery")
    public Object discovery() {
        List<String> services = client.getServices();
        System.out.println("discovery=>services:" + services);
        //遍历和打印信息
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    "instance.getHost():" + instance.getHost() + "\n" +
                            "instance.getInstanceId():" + instance.getInstanceId() + "\n" +
                            "instance.getScheme():" + instance.getScheme() + "\n" +
                            "instance.getServiceId():" + instance.getServiceId() + "\n" +
                            "instance.getMetadata():" + instance.getMetadata() + "\n" +
                            "instance.getPort():" + instance.getPort() + "\n" +
                            "instance.getUri():" + instance.getUri()
            );

        }
        return this.client;
    }
}
