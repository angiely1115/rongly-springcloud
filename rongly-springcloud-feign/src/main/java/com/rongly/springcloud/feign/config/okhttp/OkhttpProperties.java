package com.rongly.springcloud.feign.config.okhttp;

import lombok.Data;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description: okhttp属性配置类
 * @Date: 2018/10/31 14:19
 * @Version: 1.0
 * modified by:
 */
@ConfigurationProperties(prefix = "rongly.okhttp.client")
@Data
public class OkhttpProperties {

    //超时单位为S
    private int connectTimeOut = 60;
    private int readTimeOut = 60;
    private int writeTimeOut = 80;
    private boolean retryOnConnectionFailure = true;
    private boolean followRedirects = true;
    private boolean followSslRedirects = true;
    private Connection connection = new Connection();
    //拦截器
    private List<String> interceptors = new ArrayList<>();
    private List<String> httpLogInterceptors = new ArrayList<>();
    private HttpLoggingInterceptor.Level level;

    @Data
    public static class Connection{
        //最大空闲连接数
        private int maxIdleConnections = 5;
        //存活时间 单位分钟
        private long keepAliveDurationNs = 5;
    }

}
