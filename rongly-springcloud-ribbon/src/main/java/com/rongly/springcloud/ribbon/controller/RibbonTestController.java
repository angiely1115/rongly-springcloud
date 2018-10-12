package com.rongly.springcloud.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description: ribbon测试控制器
 * @Date: 2018/10/9 20:03
 * @Version: 1.0
 * modified by:
 */
@RestController
@RequestMapping("ribbon")
public class RibbonTestController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String PREFIX_URL = "http://rongly-dept/";
    private static final String PREFIX_URL2 = "http://rongly-hystrix-demo/";
    @GetMapping("ribbonDemo1")
    public List<String> ribbonDemo1(){
       List<String>  strings = restTemplate.getForObject(PREFIX_URL.concat("discovery").concat("/demo"), List.class);
       return strings;
    }

    @GetMapping("hystrixDemo1")
    public String hystrixDemo1(String name){
        String strings = restTemplate.getForObject(PREFIX_URL.concat("hello/hystrix").concat("/demo1?name=").concat(name), String.class);
        return strings;
    }
}
