package com.rongly.springcloud.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/30 13:58
 * @Version: 1.0
 * modified by:
 */
@FeignClient(value = "rongly-hystrix-demo",fallbackFactory = HystrixDemoFeignFallback.class)
@Component
public interface HystrixDemoFeign {
    @GetMapping("/hello/hystrix/demo1")
     String hystrix01(@RequestParam(value = "name",required = false) String name);

    @GetMapping("/hello/hystrix/demo2")
    String hystrix02(@RequestParam(value = "name",required = false) String name);
    @GetMapping("/hello/hystrix/cacheHystrix01")
    String cacheHystrix01(@RequestParam(value = "name",required = false) String name,@RequestParam(value = "id",required = false) String id);
    @GetMapping("/hello/hystrix/mergeHystrix")
    List<String> mergeHystrix(@RequestParam(value = "ids",required = false) List<String> ids);
}
