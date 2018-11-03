package com.rongly.springcloud.zuul.demo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @Author: lvrongzhuan
 * @Description: 自定义过滤器
 * @Date: 2018/11/1 11:33
 * @Version: 1.0
 * modified by:
 */
public class MyFirstFilter extends ZuulFilter{
    /**
     * 过滤器类型 可以是 pre route post error
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 核心逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("这是我第一个自定义的 Zuul filter");
        return null;
    }
}
