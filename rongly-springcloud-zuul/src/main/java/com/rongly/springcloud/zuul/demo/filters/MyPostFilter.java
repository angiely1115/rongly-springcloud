package com.rongly.springcloud.zuul.demo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/1 12:05
 * @Version: 1.0
 * modified by:
 */
public class MyPostFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("这是post filter");
        RequestContext requestContext = RequestContext.getCurrentContext();
        String responseBody =  requestContext.getResponseBody();
//        requestContext.getResponse().setCharacterEncoding("UTF-8");
        if(StringUtils.isNotBlank(responseBody)){

        }
        return null;
    }
}
