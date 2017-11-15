package cn.tac.framework.easydev.core.config;

import cn.tac.framework.easydev.core.constant.GlobalConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 与开发框架相关的一些全局配置
 *
 * @author tac
 * @since 01/11/2017
 */
@ConfigurationProperties(GlobalConstant.CONFIG_PROP_PREFIX)
public class EasyCoreProperties {
    public EasyCoreProperties() {
        formatPattern = new FormatPattern();
    }

    private boolean compactUUID = true;

    private FormatPattern formatPattern;

    public boolean isCompactUUID() {
        return compactUUID;
    }

    public void setCompactUUID(boolean compactUUID) {
        this.compactUUID = compactUUID;
    }

    public FormatPattern getFormatPattern() {
        return formatPattern;
    }

    public void setFormatPattern(FormatPattern formatPattern) {
        this.formatPattern = formatPattern;
    }

    public class FormatPattern {
        private String date = "yyyy-MM-dd";
        private String datetime = "yyyy-MM-dd HH:mm:ss";

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }
    }
}
