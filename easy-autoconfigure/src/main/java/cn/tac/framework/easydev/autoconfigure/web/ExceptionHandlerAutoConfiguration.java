package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.web.exception.handler.DefaultGlobalExceptionHandler;
import cn.tac.framework.easydev.web.exception.handler.ExceptionHandler;
import cn.tac.framework.easydev.web.exception.handler.config.WebExceptionHandlerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
@ConditionalOnClass(ExceptionHandler.class)
@EnableConfigurationProperties(WebExceptionHandlerProperties.class)
public class ExceptionHandlerAutoConfiguration extends WebMvcConfigurerAdapter {
    private WebExceptionHandlerProperties webExceptionHandlerProperties;

    @Autowired
    public void setWebExceptionHandlerProperties(WebExceptionHandlerProperties webExceptionHandlerProperties) {
        this.webExceptionHandlerProperties = webExceptionHandlerProperties;
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
        exceptionResolvers.add(new DefaultGlobalExceptionHandler(webExceptionHandlerProperties)::process);
    }
}
