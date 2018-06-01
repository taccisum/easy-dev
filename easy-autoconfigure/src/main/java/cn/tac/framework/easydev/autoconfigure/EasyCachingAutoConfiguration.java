package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.autoconfigure.caching.RedisCacheAutoConfiguration;
import cn.tac.framework.easydev.caching.core.config.CachingCoreProperties;
import cn.tac.framework.easydev.caching.core.constant.CachingCoreConstant;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
@EnableCaching
@ConditionalOnClass(CachingCoreProperties.class)
@ConditionalOnProperty(name = CachingCoreConstant.CONFIG_PROP_PREFIX + ".enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(CachingCoreProperties.class)
@ImportAutoConfiguration({EasyCoreAutoConfiguration.class, RedisCacheAutoConfiguration.class})
public class EasyCachingAutoConfiguration {
}
