package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.web.response.wrapper.ResponseWrapperReturnValueHandler;
import cn.tac.framework.easydev.web.response.wrapper.config.ResponseWrapperProperties;
import cn.tac.framework.easydev.web.response.wrapper.util.ResponseWrapperConfigurator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * @author tac
 * @since 2.2
 */
@Configuration
@ConditionalOnClass(ResponseWrapperProperties.class)
@EnableConfigurationProperties(ResponseWrapperProperties.class)
public class ResponseWrapperAutoConfiguration implements InitializingBean {
    @Autowired
    private ResponseWrapperProperties properties;
    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        ResponseWrapperConfigurator.insertHandler(
                adapter,
                new ResponseWrapperReturnValueHandler(new RequestResponseBodyMethodProcessor(adapter.getMessageConverters())),
                RequestResponseBodyMethodProcessor.class);
    }
}
