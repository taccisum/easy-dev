package cn.tac.framework.easydev.caching.redis;

import cn.tac.framework.easydev.caching.core.api.CacheManagerFactoryBean;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;

/**
 * @author tac
 * @since 2.0
 */
public class RedisCacheManagerFactoryBean extends CacheManagerFactoryBean<RedisCacheManager> {
    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        Objects.requireNonNull(redisTemplate, "redisTemplate");
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected RedisCacheManager getCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(60);
        return cacheManager;
    }
}
