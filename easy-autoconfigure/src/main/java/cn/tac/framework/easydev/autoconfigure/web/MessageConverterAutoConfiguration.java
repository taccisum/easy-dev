package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.web.messageconverter.ObjectMapperFactoryBean;
import cn.tac.framework.easydev.web.messageconverter.config.MessageConverterProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    @Autowired
    private MessageConverterProperties messageConverterProperties;

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapperFactoryBean objectMapperFactoryBean() {
        ObjectMapperFactoryBean bean = new ObjectMapperFactoryBean();
        String dateFormatPattern = StringUtils.isNotBlank(messageConverterProperties.getDateFormatPattern()) ?
                messageConverterProperties.getDateFormatPattern() :
                easyCoreProperties.getFormatPattern().getDatetime();
        bean.setDateFormatPattern(dateFormatPattern);
        bean.setLong2String(messageConverterProperties.getLongToString());
        return bean;
    }
}
