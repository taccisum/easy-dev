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
    private String defaultView = "error";

    public Boolean getOutputStackTrace() {
        return outputStackTrace;
    }

    public void setOutputStackTrace(Boolean outputStackTrace) {
        this.outputStackTrace = outputStackTrace;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }
}
