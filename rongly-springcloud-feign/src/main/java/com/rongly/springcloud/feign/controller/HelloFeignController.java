package com.rongly.springcloud.feign.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.rongly.springcloud.feign.feign.DeptFeign;
import com.rongly.springcloud.feign.feign.HystrixDemoFeign;
import com.rongly.springcloud.feign.feign.OtherServerDemoFeign;
import com.rongly.springcloud.feign.service.ReqMergeDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: lvrongzhuan
 * @Description: feign联系
 * @Date: 2018/10/10 10:08
 * @Version: 1.0
 * modified by:
 */
@Slf4j
@RestController
@RequestMapping("hello/feign")
public class HelloFeignController {
    @Autowired
    private DeptFeign deptFeign;
    @Autowired
    private HystrixDemoFeign hystrixDemoFeign;
    @Autowired
    private OtherServerDemoFeign otherServerDemoFeign;
    @Autowired
    private ReqMergeDemoService reqMergeDemoService;

    @GetMapping("discovery")
    public List<String> discovery(){
        return deptFeign.discovery();
    }
    @GetMapping("ecp/{name}")
    public String hystrixExceptionTest(@PathVariable(required = false) String name){
        return deptFeign.exceptionDemo(name);
    }
    @GetMapping("hystrixDegrade")
    public String hystrixDegrade(String name){
        return hystrixDemoFeign.hystrix01(name);
    }

    /**
     * 依赖服务没有容错处理
     * @param name
     * @return
     */
    @GetMapping("hystrixDegrade2")
    public String hystrixDegrade2(String name){
        return hystrixDemoFeign.hystrix02(name);
    }

    @GetMapping("other/{id}")
    public Object other01(@PathVariable("id") Long id){
        return otherServerDemoFeign.queryUserEntity(id);
    }
    @GetMapping("cacheHystrix01")
    public String cacheHystrix(String name,String id){
        hystrixDemoFeign.cacheHystrix01(name,id);
        log.info("缓存请求结果");
        return hystrixDemoFeign.cacheHystrix01(name,id);
    }

    /**
     * 请求合并
     * @param id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("reqMerge")
    public String queryProductById(String id) throws ExecutionException, InterruptedException {
//        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        Future<String> stringFuture = reqMergeDemoService.collapsing(id);
        String str = stringFuture.get();
        log.info("reqMerge 返回结果：{}",str);
//        hystrixRequestContext.close();
        return str;
    }

    /**
     * 线程隔离请求
     * @return
     */
    @GetMapping("threadIsolation")
    public String  threadIsolation(){
        log.info("Controller 请求线程:{}",Thread.currentThread().getName());
        return reqMergeDemoService.threadIsolation();
    }

    /**
     * 非线程隔离请求
     * @return
     */
    @GetMapping("no_threadIsolation")
    public String  noThreadIsolation(){
        log.info("Controller 请求线程:{}",Thread.currentThread().getName());
        return reqMergeDemoService.noThreadIsolation();
    }
}
