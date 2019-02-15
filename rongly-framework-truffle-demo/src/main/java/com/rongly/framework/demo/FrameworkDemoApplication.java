package com.rongly.framework.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.xs.rongly.framework.stater.security.autoConfig.annotation.EnableAPPSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
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
/*@ComponentScan(
        basePackages = {"com.xs.rongly.framework.stater.security.spring.security.core", "com.xs.rongly.framework.stater.security.spring.security.browser"
        }
)*/
//使用浏览器
//@EnableBrowserSecurityConfig
//@EnableAPPSecurityConfig
//使用dubbo
@EnableDubbo
public class FrameworkDemoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FrameworkDemoApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
