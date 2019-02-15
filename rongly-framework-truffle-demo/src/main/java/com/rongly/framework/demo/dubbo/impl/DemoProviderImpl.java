package com.rongly.framework.demo.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import com.alibaba.dubbo.validation.MethodValidated;
import com.rongly.dubbo.demo.api.DubboSampleDemo;
import com.rongly.dubbo.demo.domain.DemoDubboDomain;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/30 11:07
 * @Version: 1.0
 * modified by:
 */
@Service(version = "1.0.0")
@Slf4j
public class DemoProviderImpl implements DubboSampleDemo {

    @Override
    public DemoDubboDomain queryDemo() {
        log.info("简单请求。。。");
        DemoDubboDomain demoDubboDomain = new DemoDubboDomain();
        demoDubboDomain.setDemoName("你要的bubbo demo");
        demoDubboDomain.setId(1111L);
        return demoDubboDomain;
    }
}
