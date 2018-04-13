package cn.tac.framework.easydev.caching.redis;

import cn.tac.framework.easydev.caching.core.api.CacheManagerFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@EnableCaching
@ImportAutoConfiguration(RedisAutoConfiguration.class)
public class RedisCacheManagerFactoryBeanTest {
    @Autowired
    private CacheManager cacheManager;

    @TestConfiguration
    public static class Configuration {
        @Autowired
        private RedisTemplate redisTemplate;

        @Bean
        public CacheManagerFactoryBean cacheManagerFactoryBean() {
            RedisCacheManagerFactoryBean bean = new RedisCacheManagerFactoryBean();
            bean.setRedisTemplate(redisTemplate);
            return bean;
        }
    }

    @Test
    public void testSimply() {
        assertThat(cacheManager).isNotNull();
    }

    @Autowired
    private Foo foo;

    @Test
    public void testCache() {
        assertThat(foo.get("1")).isEqualTo(0);
        assertThat(foo.get("1")).isEqualTo(0);
        assertThat(foo.get("1")).isEqualTo(0);
        assertThat(foo.get("2")).isEqualTo(1);
    }

    @Component
    public static class Foo {
        private static int count = 0;

        @Cacheable("foo")
        public int get(String id) {
            return count++;
        }
    }
}
