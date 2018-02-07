package cn.tac.framework.easydev.web.messageconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;

/**
 * @author tac
 * @since 2.0
 */
public class ObjectMapperFactoryBean implements FactoryBean<ObjectMapper> {
    private String dateFormatPattern = null;

    @Override
    public ObjectMapper getObject() throws Exception {
        ObjectMapper bean = new ObjectMapper();
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
}
