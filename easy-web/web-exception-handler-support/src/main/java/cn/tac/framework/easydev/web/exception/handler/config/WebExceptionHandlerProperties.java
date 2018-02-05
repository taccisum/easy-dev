package cn.tac.framework.easydev.web.exception.handler.config;

import cn.tac.framework.easydev.web.exception.handler.constant.WebExceptionHandlerConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 2.0
 */
@ConfigurationProperties(prefix = WebExceptionHandlerConstant.CONFIG_PROP_PREFIX)
public class WebExceptionHandlerProperties {
    private Boolean outputStackTrace = false;

    public Boolean getOutputStackTrace() {
        return outputStackTrace;
    }

    public void setOutputStackTrace(Boolean outputStackTrace) {
        this.outputStackTrace = outputStackTrace;
    }
}
