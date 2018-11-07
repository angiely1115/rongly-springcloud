package com.rongly.springcloud.zuul.config.okhttp;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.ZuulProxyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description: okhttp 配置类
 * @Date: 2018/10/31 14:52
 * @Version: 1.0
 * modified by:
 */
@Configuration
@EnableConfigurationProperties(OkhttpProperties.class)
@ConditionalOnClass(OkHttpClient.class)
@AutoConfigureAfter(
        name = {
        "org.springframework.cloud.netflix.ribbon.okhttp.OkHttpRibbonConfiguration",
})
@AutoConfigureBefore(value = ZuulProxyAutoConfiguration.class)
public class OkHttpConfig {
    @Autowired
    private OkhttpProperties okhttpProperties;


    /**
     * 连接池配置
     * @return
     */
    @ConditionalOnMissingBean
    @Bean
    public ConnectionPool connectionPool(){
        ConnectionPool connectionPool = new ConnectionPool(okhttpProperties.getConnection().getMaxIdleConnections(),okhttpProperties.getConnection().getKeepAliveDurationNs(), TimeUnit.MINUTES);
        return connectionPool;
    }

    @Autowired(required = false)
    private HttpLoggingInterceptor.Logger logger;

    @Bean
    @ConditionalOnMissingBean
    public HttpLoggingInterceptor okHttp3LoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor;

        if (logger != null) {
            httpLoggingInterceptor = new HttpLoggingInterceptor(logger);
        } else {
            httpLoggingInterceptor = new HttpLoggingInterceptor();
        }

        HttpLoggingInterceptor.Level level = okhttpProperties.getLevel();
        if (level != null) {
            httpLoggingInterceptor.setLevel(level);
        }

        return httpLoggingInterceptor;
    }

    @Bean("okHttpClient")
    public okhttp3.OkHttpClient okHttpClient(){
        okhttp3.OkHttpClient.Builder builder =  new okhttp3.OkHttpClient().newBuilder();
        okhttp3.OkHttpClient okHttpClient = builder.readTimeout(okhttpProperties.getReadTimeOut(),TimeUnit.SECONDS)
                .writeTimeout(okhttpProperties.getWriteTimeOut(),TimeUnit.SECONDS)
                .connectionPool( connectionPool())
                .connectTimeout(okhttpProperties.getConnectTimeOut(),TimeUnit.SECONDS)
                .addInterceptor(okHttp3LoggingInterceptor())
                .retryOnConnectionFailure(okhttpProperties.isRetryOnConnectionFailure())
                .followRedirects(okhttpProperties.isFollowRedirects())
                .build();
        return okHttpClient;
    }

    @Bean
    public MyOkHttpClient myOkHttpClient(){
        return new MyOkHttpClient(okHttpClient());
    }

}
