package com.rongly.framework.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/24 11:31
 * @Version: 1.0
 * modified by:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = {"com.rongly.framework.demo.mongodb.dao"})
public class FrameworkDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrameworkDemoApplication.class,args);
    }
}
