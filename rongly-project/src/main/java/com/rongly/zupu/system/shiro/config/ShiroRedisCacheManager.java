package com.rongly.zupu.system.shiro.config;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * shiro使用redis分布缓存来管理

@Component
@Primary
@ConditionalOnProperty(name = "rongly.shiro.cache",havingValue = "redis",matchIfMissing = true)
 */
public class ShiroRedisCacheManager extends AbstractCacheManager{

    @Autowired
    private RedisTemplate<byte[],byte[]> redisTemplateByte;
    @Autowired
    private ShiroProperties shiroProperties;
 
    //为了个性化配置redis存储时的key，我们选择了加前缀的方式，所以写了一个带名字及redis操作的构造函数的Cache类
    @Override
    public Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache(redisTemplateByte,name,shiroProperties.getExpireIn());
    }

}