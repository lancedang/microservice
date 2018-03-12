package com.microservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        List<ServiceInstance> helloInstances = discoveryClient.getInstances("hello-service");
        List<ServiceInstance> apiInstances = discoveryClient.getInstances("api-gateway");

        System.out.println("helloInstances count = " + helloInstances.size());
        ServiceInstance helloInstance = helloInstances.get(0);
        //host=Dangdang.mshome.net, serviceId=HELLO-SERVICE, port=8081, uri=http://Dangdang.mshome.net:8081
        System.out.println("host=" + helloInstance.getHost() + ", serviceId=" + helloInstance.getServiceId() + ", port=" + helloInstance.getPort() + ", uri=" + helloInstance.getUri());

        System.out.println("apiInstances count = " + apiInstances.size());
        ServiceInstance apiInstance = apiInstances.get(0);
        System.out.println("host=" + apiInstance.getHost() + ", serviceId=" + apiInstance.getServiceId() + ", port=" + apiInstance.getPort() + ", uri=" + apiInstance.getUri());

        return "hello";
    }

}
