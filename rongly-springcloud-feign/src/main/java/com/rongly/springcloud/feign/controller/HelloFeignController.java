package com.rongly.springcloud.feign.controller;

import com.rongly.springcloud.feign.feign.DeptFeign;
import com.rongly.springcloud.feign.feign.HystrixDemoFeign;
import com.rongly.springcloud.feign.feign.OtherServerDemoFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description: feign联系
 * @Date: 2018/10/10 10:08
 * @Version: 1.0
 * modified by:
 */
@RestController
@RequestMapping("hello/feign")
public class HelloFeignController {
    @Autowired
    private DeptFeign deptFeign;
    @Autowired
    private HystrixDemoFeign hystrixDemoFeign;
    @Autowired
    private OtherServerDemoFeign otherServerDemoFeign;

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

}
