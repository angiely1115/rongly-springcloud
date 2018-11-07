package com.rongly.springcloud.feign.feign;

import com.rongly.springcloud.feign.config.ConstantConfig;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/30 14:04
 * @Version: 1.0
 * modified by:
 */
@Component
public class HystrixDemoFeignFallback implements FallbackFactory<HystrixDemoFeign> {
    @Override
    public HystrixDemoFeign create(Throwable throwable) {
        return new HystrixDemoFeign() {
            @Override
            public String hystrix01(String name) {
                throwable.printStackTrace();
                return "服务降级了，已经停止,"+name;
            }

            @Override
            public String hystrix02(String name) {
                throwable.printStackTrace();
                return "依赖服务没有容错处理,"+name;
            }

            @Override
            public String cacheHystrix01(String name, String id) {
                throwable.printStackTrace();
                return "依赖服务没有容错处理,"+name;
            }

            @Override
            public List<String> mergeHystrix(List<String> ids) {
                throwable.printStackTrace();
                return ids;
            }
        };
    }
}
