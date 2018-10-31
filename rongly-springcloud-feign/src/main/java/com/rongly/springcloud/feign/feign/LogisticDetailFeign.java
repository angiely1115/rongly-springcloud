package com.rongly.springcloud.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/30 17:21
 * @Version: 1.0
 * modified by:
 */
@FeignClient(name = "LogisticDetailFeign",url = "http://111.11",fallbackFactory = LogisticDetailFeignFallback.class)
@Component
public interface LogisticDetailFeign {
    @GetMapping("/{path}")
    String queryLogistic(@PathVariable("path") String path, @RequestHeader("Authorization") String a, @RequestParam("n") String n, @RequestParam("t") String t);
}
