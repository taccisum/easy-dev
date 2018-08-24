package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.autoconfigure.web.*;
import cn.tac.framework.easydev.web.core.config.WebCoreProperties;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author tac
 * @since 2.0
 */
@ConditionalOnClass(WebCoreProperties.class)
@ConditionalOnWebApplication
@Configuration
@ImportAutoConfiguration({
        EasyDaoAutoConfiguration.class,
        ExceptionHandlerAutoConfiguration.class,
        SwaggerAutoConfiguration.class,
        MessageConverterAutoConfiguration.class,
        ResponseWrapperAutoConfiguration.class,
        ArgumentsValidationSupportAutoConfiguration.class,
        FeignClientSupportAutoConfiguration.class
})
public class EasyWebAutoConfiguration {
}
