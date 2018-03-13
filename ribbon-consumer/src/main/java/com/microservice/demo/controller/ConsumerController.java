package com.microservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Dangdang on 2018/3/14.
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public String testLoadBalance() {

        String helloServiceUrl="http://hello-service/hello/test";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(helloServiceUrl, String.class);
        String result = responseEntity.getBody();

        return result;
    }

}
