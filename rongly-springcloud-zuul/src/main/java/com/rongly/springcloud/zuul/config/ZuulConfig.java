package com.rongly.springcloud.zuul.config;

import com.rongly.springcloud.zuul.demo.filters.MyFirstFilter;
import com.rongly.springcloud.zuul.demo.filters.MyPostFilter;
import com.rongly.springcloud.zuul.demo.filters.MySecondFilter;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/1 11:38
 * @Version: 1.0
 * modified by:
 */
@Configuration
@RefreshScope
public class ZuulConfig {

    private  HttpLoggingInterceptor.Level http_log_level;
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

    @Bean
    @ConditionalOnMissingBean
    public HttpLoggingInterceptor okHttp3LoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor;
            httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public HttpLoggingInterceptor.Level getHttp_log_level() {
        return http_log_level;
    }

    public void setHttp_log_level(HttpLoggingInterceptor.Level http_log_level) {
        this.http_log_level = http_log_level;
    }
}
