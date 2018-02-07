package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.web.messageconverter.ObjectMapperFactoryBean;
import cn.tac.framework.easydev.web.messageconverter.config.MessageConverterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author tac
 * @since 2.0
 */
@ConditionalOnClass(MessageConverterProperties.class)
@EnableConfigurationProperties(MessageConverterProperties.class)
public class MessageConverterAutoConfiguration {
    @Autowired
    private EasyCoreProperties easyCoreProperties;

    @Bean
    public ObjectMapperFactoryBean objectMapperFactoryBean() {
        ObjectMapperFactoryBean bean = new ObjectMapperFactoryBean();
        bean.setDateFormatPattern(easyCoreProperties.getFormatPattern().getDatetime());
        return bean;
    }
}
