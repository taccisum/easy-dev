package cn.tac.framework.easydev.web.core.config;

import cn.tac.framework.easydev.web.core.constant.WebCoreConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 05/02/2018
 */
@ConfigurationProperties(prefix = WebCoreConstant.CONFIG_PROP_PREFIX)
public class WebCoreProperties {
}
