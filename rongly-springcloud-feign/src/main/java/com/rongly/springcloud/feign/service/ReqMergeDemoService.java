package com.rongly.springcloud.feign.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rongly.springcloud.feign.feign.HystrixDemoFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Author: lvrongzhuan
 * @Description: demo
 * @Date: 2018/11/6 17:12
 * @Version: 1.0
 * modified by:
 */
@Slf4j
@Service
public class ReqMergeDemoService {
    @Autowired
    private HystrixDemoFeign hystrixDemoFeign;
    @HystrixCollapser(batchMethod = "collapsingList",scope= com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "3000")
    })
    public Future<String> collapsing(String id){
        return null;
    }
    @HystrixCommand
    public List<String> collapsingList(List<String> ids){
      return   hystrixDemoFeign.mergeHystrix(ids);
    }

    @HystrixCommand
    public String threadIsolation(){
        log.info("当前线程：{}",Thread.currentThread().getName());
        return "线程隔离请求，"+Thread.currentThread().getName();
    }

    public String noThreadIsolation(){
        log.info("当前线程：{}",Thread.currentThread().getName());
        return "不是线程隔离请求，"+Thread.currentThread().getName();
    }
}
