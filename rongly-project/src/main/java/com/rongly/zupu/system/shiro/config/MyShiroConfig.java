package com.rongly.zupu.system.shiro.config;

import com.rongly.zupu.system.shiro.ShiroLoginFilter;
import com.xs.rongly.framework.stater.security.autoConfig.annotation.EnableShiroConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/21 11:59
 * @Version: 1.0
 * modified by:
 */
@Configuration
@EnableShiroConfig
public class MyShiroConfig {
    @Bean("shiroLoginFilter")
    public ShiroLoginFilter shiroLoginFilter(){
        return  new ShiroLoginFilter();
    }
}
