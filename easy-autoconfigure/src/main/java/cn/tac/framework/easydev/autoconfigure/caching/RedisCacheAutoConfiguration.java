package cn.tac.framework.easydev.autoconfigure.caching;

import cn.tac.framework.easydev.caching.core.api.CacheManagerFactoryBean;
import cn.tac.framework.easydev.caching.redis.RedisCacheManagerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
public class RedisCacheAutoConfiguration {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public CacheManagerFactoryBean cacheManagerFactoryBean() {
        RedisCacheManagerFactoryBean bean = new RedisCacheManagerFactoryBean();
        bean.setRedisTemplate(redisTemplate);
        return bean;
    }
}
