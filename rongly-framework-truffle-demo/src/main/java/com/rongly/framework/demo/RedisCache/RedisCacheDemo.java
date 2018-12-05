package com.rongly.framework.demo.RedisCache;

import com.vip.vjtools.vjkit.collection.MapUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/24 18:47
 * @Version: 1.0
 * modified by:
 */
@Service
public class RedisCacheDemo {

    @Cacheable(cacheNames="mycacheName",key = "'mymap2'",unless = "#result==null")
    public Map addMap(){
        Map map = MapUtil.newHashMap();
        map.put("map1","kkkk");
        return map;
    }
}
