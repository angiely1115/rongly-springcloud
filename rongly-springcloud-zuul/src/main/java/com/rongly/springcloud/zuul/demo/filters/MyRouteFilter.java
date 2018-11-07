package com.rongly.springcloud.zuul.demo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/6 9:39
 * @Version: 1.0
 * modified by:
 */
@Component
@Slf4j
public class MyRouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("this is my ruteFilter");
        return null;
    }
}
