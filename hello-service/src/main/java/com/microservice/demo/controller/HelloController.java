package com.microservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dangdang on 2018/3/11.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    //主类开启 EnableDiscoverClient 后, 上下文自动注入该bean
    //该类能与 eureka server 通信, 从 server 上获取各种服务相关信息
    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello() {

        //与 server 交互 获取以 serviceId 为标志的 service
        List<ServiceInstance> helloInstances = discoveryClient.getInstances("hello-service");

        List<URI> uriList = helloInstances.stream().map(item -> item.getUri()).collect(Collectors.toList());

        System.out.println(uriList.toString());

        return uriList.toString();
    }

}
