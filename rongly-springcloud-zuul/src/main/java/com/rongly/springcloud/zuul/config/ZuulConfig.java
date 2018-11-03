package com.rongly.springcloud.zuul.config;

import com.rongly.springcloud.zuul.demo.filters.MyFirstFilter;
import com.rongly.springcloud.zuul.demo.filters.MyPostFilter;
import com.rongly.springcloud.zuul.demo.filters.MySecondFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/1 11:38
 * @Version: 1.0
 * modified by:
 */
@Configuration
public class ZuulConfig {

    @Bean
    public MyFirstFilter myFirstFilter(){
        return new MyFirstFilter();
    }

    @Bean
    public MySecondFilter mySecondFilter(){
        return new MySecondFilter();
    }

    @Bean
    public MyPostFilter myPostFilter(){
        return new MyPostFilter();
    }
}
