package com.rongly.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
/**
 * 当我config Server服务down 也不影响服务 因为在依赖服务启动的时候会pull一份文件到自己的临时目录
 */
public class RonglySpringcloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(RonglySpringcloudConfigApplication.class, args);
	}
}
