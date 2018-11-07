package com.rongly.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 当我config Server服务down 也不影响服务 因为在依赖服务启动的时候会pull一份文件到自己的临时目录
 */
@SpringBootApplication
@EnableConfigServer
//让其他服务可以发现自己
@EnableDiscoveryClient
@EnableEurekaClient
public class RonglySpringcloudConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(RonglySpringcloudConfigApplication.class, args);
    }
}
