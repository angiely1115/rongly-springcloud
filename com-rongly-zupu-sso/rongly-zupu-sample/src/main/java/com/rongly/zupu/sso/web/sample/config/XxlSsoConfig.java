package com.rongly.zupu.sso.web.sample.config;

import com.rongly.zupu.core.conf.Conf;
import com.rongly.zupu.core.filter.XxlSsoWebFilter;
import com.rongly.zupu.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xuxueli 2018-11-15
 */
@Configuration
public class XxlSsoConfig {

    @Value("${xxl.sso.server}")
    private String xxlSsoServer;

    @Value("${xxl.sso.logout.path}")
    private String xxlSsoLogoutPath;

    @Value("${xxl.sso.excluded.paths}")
    private String xxlSsoExcludedPaths;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {
        // xxl-sso, redis init
        JedisUtil.init(redisTemplate);
        // xxl-sso, filter init
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("XxlSsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new XxlSsoWebFilter());
        registration.addInitParameter(Conf.SSO_SERVER, xxlSsoServer);
        registration.addInitParameter(Conf.SSO_LOGOUT_PATH, xxlSsoLogoutPath);
        registration.addInitParameter(Conf.SSO_EXCLUDED_PATHS, xxlSsoExcludedPaths);
        return registration;
    }

}
