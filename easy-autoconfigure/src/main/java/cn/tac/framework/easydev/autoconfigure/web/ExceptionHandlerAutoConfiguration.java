package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.web.exception.handler.DefaultGlobalExceptionHandler;
import cn.tac.framework.easydev.web.exception.handler.ExceptionHandler;
import cn.tac.framework.easydev.web.exception.handler.config.WebExceptionHandlerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * @author tac
 * @since 2.0
 */
@ConditionalOnClass(ExceptionHandler.class)
@EnableConfigurationProperties(WebExceptionHandlerProperties.class)
public class ExceptionHandlerAutoConfiguration {
    @Bean
    public HandlerExceptionResolver handlerExceptionResolver(WebExceptionHandlerProperties webExceptionHandlerProperties) {
        DefaultGlobalExceptionHandler handler = new DefaultGlobalExceptionHandler(webExceptionHandlerProperties);
        return handler::process;
    }
}
