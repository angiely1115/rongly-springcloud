package com.rongly.springcloud.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description: 使用feign进行服务调用，默认采用轮询算法，跟ribbon一样
 * @Date: 2018/10/10 10:11
 * @Version: 1.0
 * modified by:
 */
@FeignClient(name = "rongly-dept",fallbackFactory=DeptFeignFallback.class)
@Component
public interface DeptFeign {
    /**
     * 服务发现信息
     * @return
     */
    @GetMapping("discovery/demo")
    public List<String> discovery();
    @GetMapping("discovery/cpt/{name}")
    String exceptionDemo(@PathVariable(value = "name",required = false) String name);
}
