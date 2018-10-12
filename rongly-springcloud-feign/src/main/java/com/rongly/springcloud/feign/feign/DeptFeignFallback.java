package com.rongly.springcloud.feign.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:熔断处理返回结果
 * feign.hystrix.enabled: true 需要配置这个才生效
 * @Date: 2018/10/10 11:50
 * @Version: 1.0
 * modified by:
 */
@Component
public class DeptFeignFallback implements FallbackFactory<DeptFeign> {
    @Override
    public DeptFeign create(Throwable throwable) {
        return new DeptFeign() {
            @Override
            public List<String> discovery() {
               List<String> strings =  new ArrayList();
                strings.add("熔断处理，服务没有启动");
                return strings;
            }

            @Override
            public String exceptionDemo(String name) {
                return "熔断,服务异常";
            }
        };
    }
}
