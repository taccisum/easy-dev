package cn.tac.framework.easydev.web.messageconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

/**
 * @author tac
 * @since 2.0
 */
public class GenericObjectMapperBuilder implements ObjectMapperBuilder {
    private String dateFormatPattern = null;
    private boolean long2String = true;

    public GenericObjectMapperBuilder dateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
        return this;
    }

    public GenericObjectMapperBuilder long2String(boolean long2String) {
        this.long2String = long2String;
        return this;
    }

    @Override
    public ObjectMapper build() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (long2String) {
            SimpleModule module = new SimpleModule();
            module.addSerializer(Long.class, ToStringSerializer.instance);
            module.addSerializer(Long.TYPE, ToStringSerializer.instance);
            objectMapper.registerModule(module);
        }
        if (StringUtils.isNotBlank(dateFormatPattern)) {
            objectMapper.setDateFormat(new SimpleDateFormat(dateFormatPattern));
        }
        return objectMapper;
    }
}
