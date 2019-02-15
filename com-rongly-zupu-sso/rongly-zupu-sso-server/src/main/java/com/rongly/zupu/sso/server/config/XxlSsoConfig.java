package com.rongly.zupu.sso.server.config;

import com.rongly.zupu.core.store.SsoLoginStore;
import com.rongly.zupu.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author xuxueli 2018-04-03 20:41:07
 */
@Configuration
public class XxlSsoConfig implements InitializingBean {


    @Value("${xxl.sso.redis.expire.minite}")
    private int redisExpireMinite;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void afterPropertiesSet() {
        SsoLoginStore.setRedisExpireMinite(redisExpireMinite);
        JedisUtil.init(redisTemplate);
    }


}
