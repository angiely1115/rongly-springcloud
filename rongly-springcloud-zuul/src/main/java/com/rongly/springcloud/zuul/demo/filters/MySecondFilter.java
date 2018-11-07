package com.rongly.springcloud.zuul.demo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/1 11:53
 * @Version: 1.0
 * modified by:
 */
public class MySecondFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
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
        RequestContext requestContext = RequestContext.getCurrentContext();
       HttpServletRequest httpServletRequest =  requestContext.getRequest();
        String a = httpServletRequest.getParameter("a");
        if(StringUtils.isBlank(a)){
            //参数a没有传
            requestContext.getResponse().setCharacterEncoding("UTF-8");
            requestContext.setSendZuulResponse(false);//禁止下游服务的访问
            requestContext.setResponseBody("{\"status\":\"403\",\"message\":                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              \"参数错误\"}");
//            requestContext.setResponseStatusCode(403);

            requestContext.set("logic-is-success",false);
            return null;
        }
        requestContext.set("logic-is-success",true);
        return null;
    }
}
