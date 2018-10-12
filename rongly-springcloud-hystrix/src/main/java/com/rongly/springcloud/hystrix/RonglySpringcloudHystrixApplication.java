package com.rongly.springcloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
public class RonglySpringcloudHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(RonglySpringcloudHystrixApplication.class, args);
	}
}
