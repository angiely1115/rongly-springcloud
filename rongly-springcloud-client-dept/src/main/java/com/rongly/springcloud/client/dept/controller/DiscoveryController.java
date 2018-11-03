package com.rongly.springcloud.client.dept.controller;

import com.netflix.eureka.util.EurekaMonitors;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
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
@Slf4j
public class DiscoveryController {
    @Value("${rongly.love.name}")
    private String ronglyLove1;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private EurekaClientConfigBean eurekaClientConfigBean;

    @GetMapping("demo")
    public List<String> discovery(){
        String description = discoveryClient.description();
        log.info("description:"+description);
        //获取服务实例列表
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("RONGLY-DEPT");
        serviceInstances.forEach((s->{
            log.info("getHost:"+s.getHost()+" getScheme:"+s.getScheme()+" getServiceId："+s.getServiceId()+" getMetadata："+s.getMetadata()+" getPort:"+s.getPort()+" getUri："+s.getUri());
//            s.isSecure() 是否开启https
            Map<String, String> stringMap = s.getMetadata();
            stringMap.forEach((k,v)->{
                log.info("Metadata-K:"+k);
                log.info("Metadata-V:"+v);
            });
        }));
       List<String> strings = discoveryClient.getServices();
       strings.add(ronglyLove1);
       return strings;
    }

    @GetMapping("eurekaClientConfigBean")
    public void eurekaClientConfigBean(){
        eurekaClientConfigBean.getInstanceInfoReplicationIntervalSeconds();
        EurekaMonitors.CANCEL.getCount();//eureka 监控信息 自启动以来收到的总取消租约次数
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
