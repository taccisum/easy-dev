package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.core.util.SpringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author tac
 * @since 13/11/2017
 */
@Configuration
@EnableConfigurationProperties(EasyCoreProperties.class)
@Import(SpringUtils.class)
public class EasyCoreAutoConfiguration {
}
