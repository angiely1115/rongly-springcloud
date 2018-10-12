package com.rongly.springcloud.client.dept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
//@EnableEurekaServer
public class RonglySpringcloudClientDeptApplication {

	public static void main(String[] args) {
		SpringApplication.run(RonglySpringcloudClientDeptApplication.class, args);
	}
}
