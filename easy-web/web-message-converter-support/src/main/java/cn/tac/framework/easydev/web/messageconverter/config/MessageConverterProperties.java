package cn.tac.framework.easydev.web.messageconverter.config;

import cn.tac.framework.easydev.web.messageconverter.constant.MessageConverterConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 2.0
 */
@ConfigurationProperties(MessageConverterConstant.CONFIG_PROP_PREFIX)
public class MessageConverterProperties {
}
