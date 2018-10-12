package com.rongly.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class RonglySpringcloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RonglySpringcloudZuulApplication.class, args);
	}
}
