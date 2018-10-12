package com.rongly.springcloud.hystrix.dashboard.ronglyspringcloudhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//服务监控
@EnableHystrixDashboard
public class RonglySpringcloudHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(RonglySpringcloudHystrixDashboardApplication.class, args);
	}
}
