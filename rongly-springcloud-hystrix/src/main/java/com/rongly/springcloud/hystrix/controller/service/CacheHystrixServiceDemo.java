package com.rongly.springcloud.hystrix.controller.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: lvrongzhuan
 * @Description: hystrix 请求缓存测试
 * @Date: 2018/11/6 15:08
 * @Version: 1.0
 * modified by:
 */
@Service
@Slf4j
public class CacheHystrixServiceDemo {

    /**
     * 缓存例子1 感觉很鸡肋 没实际意义的样子
     * @param name
     * @param id
     * @return
     */
    @CacheResult
    @HystrixCommand(commandKey = "cacheDemo1")
    public String cacheDemo1(String name,String id){
        log.info("请求参数name:{}:id {}",name,id);
        return "hystrix 缓存请求";
    }

}
