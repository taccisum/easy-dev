package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.web.messageconverter.GenericObjectMapperBuilder;
import cn.tac.framework.easydev.web.messageconverter.config.MessageConverterProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
@ConditionalOnClass(MessageConverterProperties.class)
@EnableConfigurationProperties(MessageConverterProperties.class)
public class MessageConverterAutoConfiguration extends WebMvcConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EasyCoreProperties easyCoreProperties;
    @Autowired
    private MessageConverterProperties messageConverterProperties;

    @Bean
    @ConditionalOnMissingBean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        logger.info("override default MappingJackson2HttpMessageConverter");
        String dateFormatPattern = StringUtils.isNotBlank(messageConverterProperties.getDateFormatPattern()) ?
                messageConverterProperties.getDateFormatPattern() :
                easyCoreProperties.getFormatPattern().getDatetime();
        GenericObjectMapperBuilder objectMapperBuilder = new GenericObjectMapperBuilder();
        objectMapperBuilder.dateFormatPattern(dateFormatPattern);
        logger.info("global date format pattern: {}", dateFormatPattern);
        Boolean longToString = messageConverterProperties.getLongToString();
        objectMapperBuilder.long2String(longToString);
        if(longToString) logger.info("add jackson module: serialize type long to string");

        return new MappingJackson2HttpMessageConverter(objectMapperBuilder.build());
    }
}
