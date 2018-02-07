package cn.tac.framework.easydev.web.messageconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;

/**
 * @author tac
 * @since 2.0
 */
public class ObjectMapperFactoryBean implements FactoryBean<ObjectMapper> {
    private String dateFormatPattern = null;
    private boolean long2String = true;

    @Override
    public ObjectMapper getObject() throws Exception {
        ObjectMapper bean = new ObjectMapper();
        if (long2String) {
            SimpleModule module = new SimpleModule();
            module.addSerializer(Long.class, ToStringSerializer.instance);
            module.addSerializer(Long.TYPE, ToStringSerializer.instance);
            bean.registerModule(module);
        }
        if (StringUtils.isNotBlank(dateFormatPattern)) {
            bean.setDateFormat(new SimpleDateFormat(dateFormatPattern));
        }
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        return ObjectMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    public void setLong2String(boolean long2String) {
        this.long2String = long2String;
    }
}
