package com.rongly.springcloud.feign.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/30 16:16
 * @Version: 1.0
 * modified by:
 */
@Configuration
public class ConstantConfig {
    public static String SPRING_BASE_URL = "";

    public  String getSpringBaseUrl() {
        return SPRING_BASE_URL;
    }

    @Value("${spring_base_url}")
    public  void setSpringBaseUrl(String springBaseUrl) {
        SPRING_BASE_URL = springBaseUrl;
    }
}
