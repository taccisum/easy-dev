package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.web.argsvalid.ErrorsHandler;
import cn.tac.framework.easydev.web.argsvalid.handler.MultiErrorsHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tac
 * @since 2.3
 */
@Configuration
@ConditionalOnClass(ErrorsHandler.class)
public class ArgumentsValidationSupportAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ErrorsHandler errorsHandler() {
        return new MultiErrorsHandler();
    }
}
