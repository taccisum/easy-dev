package cn.tac.framework.easydev.web.messageconverter;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public class MessageConverterConfigurator {
    private ObjectMapperBuilder objectMapperBuilder;

    public void setObjectMapperBuilder(ObjectMapperBuilder objectMapperBuilder) {
        this.objectMapperBuilder = objectMapperBuilder;
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapperBuilder.build()));
    }
}
