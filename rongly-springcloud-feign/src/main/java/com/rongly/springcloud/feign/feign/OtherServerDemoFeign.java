package com.rongly.springcloud.feign.feign;

import com.netflix.ribbon.proxy.annotation.Http;
import com.rongly.springcloud.feign.config.ConstantConfig;
import feign.HeaderMap;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.annotation.ServletSecurity;

import static com.rongly.springcloud.feign.config.ConstantConfig.SPRING_BASE_URL;

/**
 * @Author: lvrongzhuan
 * @Description: 通过feign请求不是注册到注册中心的服务
 * @Date: 2018/10/30 16:10
 * @Version: 1.0
 * modified by:
 */
@FeignClient(name = "other-demo",url = "${spring_base_url}",fallbackFactory = OtherServerDemoFeignFallback.class)
@Component
public interface OtherServerDemoFeign {
    @GetMapping("/person/query/user/{id}")
    Object queryUserEntity(@PathVariable ("id") Long id);
}
