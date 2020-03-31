package com.chengfeng.springcloud.controller;

import com.chengfeng.springcloud.pojo.Dept;
import com.chengfeng.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    //消费者不应该有service层,springboot支持restful风格；
    //restTemplate供我们直接调用；注册到spring中
    //提供多种便捷访问远程http服务的方法，简单的restful服务模块
    @Autowired
    private RestTemplate restTemplate;

    //未实现负载均衡之前url地址是常量，使用 public static final String REST_URL_PREFIX = "http://localhost:8001/";
//    public static final String REST_URL_PREFIX = "http://localhost:8001/";
//实现负载均衡之后url地址应该是变量：此时通过服务名来访问，因为当一个服务就算发布到多个注册中心的时候，其application名称是不变的
//    public static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    //    feign改造的话就不是直接写死（final），而是通过autowrited，依赖注入
    @Autowired
    private DeptClientService service = null;

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
//        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, boolean.class);
        return this.service.addDept(dept);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        //需要理解
        //return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
        return this.service.queryById(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        //return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
        return this.service.queryAll();
    }
}
