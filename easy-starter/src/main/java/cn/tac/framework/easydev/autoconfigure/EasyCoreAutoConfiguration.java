package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tac
 * @since 13/11/2017
 */
@Configuration
@EnableConfigurationProperties(EasyCoreProperties.class)
public class EasyCoreAutoConfiguration {
}
