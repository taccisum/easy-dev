package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.autoconfigure.caching.RedisCacheAutoConfiguration;
import cn.tac.framework.easydev.caching.core.api.CacheManagerFactoryBean;
import cn.tac.framework.easydev.caching.core.config.CachingCoreProperties;
import cn.tac.framework.easydev.caching.redis.RedisCacheManagerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
@EnableCaching
@ConditionalOnClass(CachingCoreProperties.class)
@EnableConfigurationProperties(CachingCoreProperties.class)
@Import({RedisCacheAutoConfiguration.class})
public class EasyCachingAutoConfiguration {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public CacheManagerFactoryBean cacheManagerFactoryBean() {
        RedisCacheManagerFactoryBean bean = new RedisCacheManagerFactoryBean();
        bean.setRedisTemplate(redisTemplate);
        return bean;
    }
}
