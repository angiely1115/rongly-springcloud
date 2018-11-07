package com.rongly.springcloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.rongly.springcloud.hystrix.controller.service.CacheHystrixServiceDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description:Hystrix 熔断处理控制器
 * @Date: 2018/10/10 14:37
 * @Version: 1.0
 * modified by:
 */
@Slf4j
@RestController
@RequestMapping("hello/hystrix")
public class HystrixController {
    @Autowired
    private CacheHystrixServiceDemo cacheHystrixServiceDemo;

    @GetMapping("demo1")
    @HystrixCommand(fallbackMethod="hystrix01_fallback")
    public String hystrix01(String name) throws InterruptedException {
        if(StringUtils.isBlank(name)){
            throw new RuntimeException("参数错误");
        }
        TimeUnit.SECONDS.sleep(1);
         name = "love_".concat(name);
         return name;
    }

    public String hystrix01_fallback(String name){
        return "异常啦 哈哈哈";
    }

    /**
     * 没有容错处理
     * @param name
     * @return
     */
    @GetMapping("demo2")
    public String hystrix02(String name) throws InterruptedException {
        if(StringUtils.isBlank(name)){
            throw new RuntimeException("参数错误");
        }
        TimeUnit.SECONDS.sleep(3);
        name = "love_".concat(name);
        return name;
    }

    @GetMapping("cacheHystrix01")
    public String cacheHystrix(String name,String id){
        HystrixRequestContext.initializeContext();
        log.info("入口请求参数:name:{},id:{}",name,id);
        cacheHystrixServiceDemo.cacheDemo1(name,id);
        cacheHystrixServiceDemo.cacheDemo1(name,id);
        return cacheHystrixServiceDemo.cacheDemo1(name,id);
    }

    /**
     * 请求合并的服务提供
     * @param ids
     * @return
     */
    @GetMapping("mergeHystrix")
    public List<String> mergeHystrix(@RequestParam List<String> ids){
        log.info("请求参数:ids:{},与大小：{}",ids,ids.size());
        ids.stream().forEach(id->id = "merge_"+id);
        return ids;
    }
}
