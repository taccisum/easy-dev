package cn.tac.framework.easydev.caching.core.config;

import cn.tac.framework.easydev.caching.core.constant.CachingCoreConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 2.0
 */
@ConfigurationProperties(prefix = CachingCoreConstant.CONFIG_PROP_PREFIX)
public class CachingCoreProperties {
}
