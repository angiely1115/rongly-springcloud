package com.rongly.zupu.sso.web.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class XxlClientApplication {

	public static void main(String[] args) {
        SpringApplication.run(XxlClientApplication.class, args);
	}

}
