package redis;

import com.rongly.framework.demo.FrameworkDemoApplication;
import com.rongly.framework.demo.RedisCache.RedisCacheDemo;
import com.vip.vjtools.vjkit.collection.MapUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/24 18:28
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkDemoApplication.class)
public class RedisDemoTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RedisCacheDemo redisCacheDemo;

    @Test
    public void test(){
        Map map = MapUtil.newHashMap();
        map.put("map1","kkkk");
        redisTemplate.opsForValue().set("mymap1",map);
        redisTemplate.expire("mymap1",2, TimeUnit.MINUTES);
        System.out.println(stringRedisTemplate);
    }

    @Test
    public void testCache(){
        redisCacheDemo.addMap();
    }

}
