package cn.tac.framework.easydev.autoconfigure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public class WebMvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    private HandlerExceptionResolver handlerExceptionResolver;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setHandlerExceptionResolver(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
        exceptionResolvers.add(handlerExceptionResolver);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        ObjectMapper objectMapper;
        try {
            objectMapper = applicationContext.getBean(ObjectMapper.class);
        } catch (NoSuchBeanDefinitionException e) {
            objectMapper = new ObjectMapper();
        }
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
