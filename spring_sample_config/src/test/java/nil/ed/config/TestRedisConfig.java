package nil.ed.config;

import nil.ed.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

public class TestRedisConfig extends BaseTest {
    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Test
    public void testRedisConfig() {
        redisCacheTemplate.keys("*").stream().forEach(System.out::println);
    }
}
