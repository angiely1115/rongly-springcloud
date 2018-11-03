package com.rongly.springcloud.zuul.groovy

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.exception.ZuulException
import org.codehaus.groovy.runtime.powerassert.SourceText

import java.util.function.BiConsumer

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/1 14:17
 * @Version: 1.0
 * modified by:
 */
class GroovyFilter extends ZuulFilter{
    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 10
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        Map<String,String> stringMap = context.getZuulRequestHeaders();
        stringMap.forEach(new BiConsumer<String, String>() {
            @Override
            void accept(String s, String s2) {
                println ("k:"+s+" v:"+s2)
            }
        });
        println("this is my groovy filter update")
        return null
    }
}
