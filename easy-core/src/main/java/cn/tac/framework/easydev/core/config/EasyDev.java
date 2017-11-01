package cn.tac.framework.easydev.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 与开发框架相关的一些全局配置
 *
 * @author tac
 * @since 01/11/2017
 */
@ConfigurationProperties("easy-dev")
public class EasyDev {
    public EasyDev() {
        formatPattern = new FormatPattern();
    }

    private FormatPattern formatPattern;
    private boolean debug;

    public FormatPattern getFormatPattern() {
        return formatPattern;
    }

    public void setFormatPattern(FormatPattern formatPattern) {
        this.formatPattern = formatPattern;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public class FormatPattern {
        private String date;
        private String datetime;

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
