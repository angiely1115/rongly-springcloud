package com.rongly.springcloud.feign.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rongly.springcloud.feign.feign.HystrixDemoFeign;
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
@Service
public class DemoService {
    @Autowired
    private HystrixDemoFeign hystrixDemoFeign;
    @HystrixCollapser(batchMethod = "collapsingList",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "1000")
    })
    public Future<String> collapsing(String id){
        return null;
    }
    @HystrixCommand
    public List<String> collapsingList(List<String> ids){
      return   hystrixDemoFeign.mergeHystrix(ids);
    }
}
