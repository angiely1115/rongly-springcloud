package com.rongly.springcloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lvrongzhuan
 * @Description:Hystrix 熔断处理控制器
 * @Date: 2018/10/10 14:37
 * @Version: 1.0
 * modified by:
 */
@RestController
@RequestMapping("hello/hystrix")
public class HystrixController {

    @GetMapping("demo1")
    @HystrixCommand(fallbackMethod="hystrix01_fallback")
    public String hystrix01(String name){
        if(StringUtils.isBlank(name)){
            throw new RuntimeException("参数错误");
        }
         name = "love_".concat(name);
         return name;
    }

    public String hystrix01_fallback(String name){
        return "异常啦 哈哈哈";
    }
}
