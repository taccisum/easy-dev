package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.web.swagger.config.SwaggerSupportProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tac
 * @since 2.0
 */
@ConditionalOnClass(SwaggerSupportProperties.class)
@EnableSwagger2
@EnableConfigurationProperties({SwaggerSupportProperties.class})
public class SwaggerAutoConfiguration {
}
