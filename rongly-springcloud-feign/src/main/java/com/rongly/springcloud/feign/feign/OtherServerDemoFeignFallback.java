package com.rongly.springcloud.feign.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/30 16:33
 * @Version: 1.0
 * modified by:
 */
@Component
public class OtherServerDemoFeignFallback implements FallbackFactory<OtherServerDemoFeign>{
    @Override
    public OtherServerDemoFeign create(Throwable throwable) {
        return new OtherServerDemoFeign() {
            @Override
            public Object queryUserEntity(Long id) {
                return "第三方服务超时";
            }
        };
    }
}
