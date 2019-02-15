package com.rongly.dubbo.module.content;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/24 11:31
 * @Version: 1.0
 * modified by:
 */
/*@ComponentScan(
        basePackages = {"com.xs.rongly.framework.stater.security.spring.security.core", "com.xs.rongly.framework.stater.security.spring.security.browser"
        }
)*/
//使用浏览器
//@EnableBrowserSecurityConfig
//@EnableAPPSecurityConfig
//使用dubbo
@EnableDubbo(scanBasePackages="com.rongly.dubbo.module")
@SpringBootApplication
public class DubboComsumeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboComsumeDemoApplication.class,args);
    }
}
