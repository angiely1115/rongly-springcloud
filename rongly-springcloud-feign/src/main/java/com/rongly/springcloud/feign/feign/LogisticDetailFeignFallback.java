package com.rongly.springcloud.feign.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/30 18:17
 * @Version: 1.0
 * modified by:
 */
@Component
public class LogisticDetailFeignFallback implements FallbackFactory<LogisticDetailFeign>{
    @Override
    public LogisticDetailFeign create(Throwable throwable) {
       return new LogisticDetailFeign() {
           @Override
           public String queryLogistic(String path,String a,String n, String t) {
               throwable.printStackTrace();
               return "kkkkkk";
           }
       };
    }
}
