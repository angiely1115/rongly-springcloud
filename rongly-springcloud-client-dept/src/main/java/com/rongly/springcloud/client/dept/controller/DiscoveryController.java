package com.rongly.springcloud.client.dept.controller;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: 服务发现
 * @Date: 2018/10/9 16:44
 * @Version: 1.0
 * modified by:
 */
@RestController
@RequestMapping("discovery")
public class DiscoveryController {
    @Value("${rongly.love.name}")
    private String ronglyLove1;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("demo")
    public List<String> discovery(){
        String description = discoveryClient.description();
        System.out.println("description:"+description);
        //获取服务实例列表
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("RONGLY-DEPT");
        serviceInstances.forEach((s->{
            System.out.println("getHost:"+s.getHost()+" getScheme:"+s.getScheme()+" getServiceId："+s.getServiceId()+" getMetadata："+s.getMetadata()+" getPort:"+s.getPort()+" getUri："+s.getUri());
            Map<String, String> stringMap = s.getMetadata();
            stringMap.forEach((k,v)->{
                System.out.println("Metadata-K:"+k);
                System.out.println("Metadata-V:"+v);
            });
        }));
       List<String> strings = discoveryClient.getServices();
       strings.add(ronglyLove1);
       return strings;
    }

    /**
     * 演示异常熔断
     * @param name
     * @return
     */
    @GetMapping("cpt/{name}")
    public String exceptionDemo(@PathVariable(required = false) String name){
        if("1".equals(name)){
            throw new IllegalArgumentException("参数异常");
        }
        return name;
    }
}
