package cn.tac.framework.easydev.web.messageconverter.config;

import cn.tac.framework.easydev.web.messageconverter.constant.MessageConverterConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 2.0
 */
@ConfigurationProperties(MessageConverterConstant.CONFIG_PROP_PREFIX)
public class MessageConverterProperties {
    private String dateFormatPattern;
    private Boolean longToString = false;

    public String getDateFormatPattern() {
        return dateFormatPattern;
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    public Boolean getLongToString() {
        return longToString;
    }

    public void setLongToString(Boolean longToString) {
        this.longToString = longToString;
    }
}
