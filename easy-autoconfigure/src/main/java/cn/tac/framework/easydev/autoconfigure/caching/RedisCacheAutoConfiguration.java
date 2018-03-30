package cn.tac.framework.easydev.autoconfigure.caching;

import cn.tac.framework.easydev.caching.core.api.CacheManagerFactoryBean;
import cn.tac.framework.easydev.caching.redis.RedisCacheManagerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
public class RedisCacheAutoConfiguration {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ApplicationContext context;

    @Bean
    @ConditionalOnMissingBean
    public CacheManagerFactoryBean cacheManagerFactoryBean() {
        RedisConnectionFactory redisConnectionFactory = context.getBean(RedisConnectionFactory.class);
        if (redisConnectionFactory == null) {
            logger.warn("未找到RedisConnectionFactory的bean，部分相关的功能可能无法正常使用");
        } else {
            try {
                redisConnectionFactory.getConnection();
            } catch (Exception e) {
                logger.warn("尝试获取与redis的连接失败，部分相关的功能可能无法正常使用", e);
            }
        }
        RedisCacheManagerFactoryBean bean = new RedisCacheManagerFactoryBean();
        bean.setRedisTemplate(redisTemplate);
        return bean;
    }
}
