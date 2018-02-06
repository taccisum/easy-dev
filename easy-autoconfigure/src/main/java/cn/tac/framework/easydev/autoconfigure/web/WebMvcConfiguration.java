package cn.tac.framework.easydev.autoconfigure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public void setHandlerExceptionResolver(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
        exceptionResolvers.add(handlerExceptionResolver);
    }
}
