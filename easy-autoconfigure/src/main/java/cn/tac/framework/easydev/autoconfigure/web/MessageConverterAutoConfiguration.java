package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.web.messageconverter.GenericObjectMapperBuilder;
import cn.tac.framework.easydev.web.messageconverter.MessageConverterConfigurator;
import cn.tac.framework.easydev.web.messageconverter.config.MessageConverterProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
@ConditionalOnClass(MessageConverterProperties.class)
@EnableConfigurationProperties(MessageConverterProperties.class)
public class MessageConverterAutoConfiguration extends WebMvcConfigurerAdapter {
    @Autowired private EasyCoreProperties easyCoreProperties;
    @Autowired private MessageConverterProperties messageConverterProperties;
    @Autowired private MessageConverterConfigurator messageConverterConfigurator;

    @Bean
    @ConditionalOnMissingBean
    public MessageConverterConfigurator messageConverterConfigurator() {
        MessageConverterConfigurator bean = new MessageConverterConfigurator();
        String dateFormatPattern = StringUtils.isNotBlank(messageConverterProperties.getDateFormatPattern()) ?
                messageConverterProperties.getDateFormatPattern() :
                easyCoreProperties.getFormatPattern().getDatetime();
        GenericObjectMapperBuilder objectMapperBuilder = new GenericObjectMapperBuilder();
        objectMapperBuilder.dateFormatPattern(dateFormatPattern);
        objectMapperBuilder.long2String(messageConverterProperties.getLongToString());
        bean.setObjectMapperBuilder(objectMapperBuilder);
        return bean;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        messageConverterConfigurator.configureMessageConverters(converters);
    }
}
