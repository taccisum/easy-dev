package cn.tac.framework.easydev.caching.core.config;

import cn.tac.framework.easydev.caching.core.constant.CachingCoreConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 2.0
 */
@ConfigurationProperties(prefix = CachingCoreConstant.CONFIG_PROP_PREFIX)
public class CachingCoreProperties {
    /**
     * 是否开启缓存自动配置
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
