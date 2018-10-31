package com.rongly.springcloud.feign.controller;

import com.google.common.collect.Maps;
import com.rongly.springcloud.feign.config.okhttp.MyOkHttpBuilder;
import com.rongly.springcloud.feign.config.okhttp.MyOkHttpClient;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: okhttpclient 测试
 * @Date: 2018/10/31 17:09
 * @Version: 1.0
 * modified by:
 */
@RestController
@RequestMapping("okhttp")
public class OkHttpClientController {
    @Autowired
    private MyOkHttpClient myOkHttpClient;

    @GetMapping("get")
    public Response okHttpGet(@RequestParam(required = false) String name){
        Map<String,String> map = Maps.newHashMapWithExpectedSize(3);
        map.put("name",name);
       return myOkHttpClient.get(MyOkHttpBuilder.builder().url("http://localhost:7001/hello/hystrix/demo1").params(map).build());
    }
}
