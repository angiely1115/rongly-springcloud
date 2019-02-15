package com.rongly.dubbo.module.content;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rongly.dubbo.demo.api.DubboSampleDemo;
import org.springframework.stereotype.Service;

/**
 * @Author: lvrongzhuan
 * @Description: dubbo demo消费端
 * @Date: 2019/1/30 11:21
 * @Version: 1.0
 * modified by:
 */
@Service
public class DemoDubboComsume {
    @Reference(version = "1.0.0",group = "rongly-framework-demo")
    private DubboSampleDemo dubboSampleDemo;
    public void demoDubbo1(){

        System.out.println(dubboSampleDemo.queryDemo());
    }


    /**
     * 异步调用
     */
    public void async(){

    }
}
