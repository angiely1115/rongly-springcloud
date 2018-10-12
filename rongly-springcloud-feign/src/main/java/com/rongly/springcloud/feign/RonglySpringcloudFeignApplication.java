package com.rongly.springcloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//使用feign需要加入此注解 不然启动不了
@EnableFeignClients
@EnableDiscoveryClient
public class RonglySpringcloudFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(RonglySpringcloudFeignApplication.class, args);
	}
}
