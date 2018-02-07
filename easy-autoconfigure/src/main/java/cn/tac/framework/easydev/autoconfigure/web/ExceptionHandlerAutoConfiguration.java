package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.web.exception.handler.DefaultGlobalExceptionHandler;
import cn.tac.framework.easydev.web.exception.handler.config.WebExceptionHandlerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ConditionalOnClass(WebExceptionHandlerProperties.class)
@EnableConfigurationProperties(WebExceptionHandlerProperties.class)
public class ExceptionHandlerAutoConfiguration extends WebMvcConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebExceptionHandlerProperties webExceptionHandlerProperties;

    @Autowired
    public void setWebExceptionHandlerProperties(WebExceptionHandlerProperties webExceptionHandlerProperties) {
        this.webExceptionHandlerProperties = webExceptionHandlerProperties;
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        if (logger.isInfoEnabled()) {
            logger.info("扩展全局的异常处理器");
        }
        super.extendHandlerExceptionResolvers(exceptionResolvers);
        DefaultGlobalExceptionHandler handler = new DefaultGlobalExceptionHandler(webExceptionHandlerProperties);
        if(logger.isTraceEnabled()){
            logger.debug("handler: {}", handler.getClass());
        }
        exceptionResolvers.add(handler);
    }
}
