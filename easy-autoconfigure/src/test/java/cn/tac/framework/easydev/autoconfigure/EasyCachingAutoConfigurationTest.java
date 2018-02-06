package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.caching.core.config.CachingCoreProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@ImportAutoConfiguration(classes = {EasyCachingAutoConfiguration.class, RedisAutoConfiguration.class})
public class EasyCachingAutoConfigurationTest {
    @Autowired private CachingCoreProperties cachingCoreProperties;
    @Autowired private StringRedisTemplate redisTemplate;
    @Autowired private CacheManager cacheManager;

    @Test
    public void testSimply() {
        assertThat(cachingCoreProperties).isNotNull();
        assertThat(redisTemplate).isNotNull();
        assertThat(cacheManager).isNotNull();
        assertThat(cacheManager).isInstanceOf(RedisCacheManager.class);
    }
}
