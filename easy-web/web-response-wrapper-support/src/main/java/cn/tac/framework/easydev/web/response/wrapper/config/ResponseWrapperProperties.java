package cn.tac.framework.easydev.web.response.wrapper.config;

import cn.tac.framework.easydev.web.response.wrapper.constant.ResponseWrapperConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 2.2
 */
@ConfigurationProperties(ResponseWrapperConstant.CONFIG_PROP_PREFIX)
public class ResponseWrapperProperties {
}
