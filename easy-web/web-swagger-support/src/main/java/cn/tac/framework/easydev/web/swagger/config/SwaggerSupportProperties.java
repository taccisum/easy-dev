package cn.tac.framework.easydev.web.swagger.config;

import cn.tac.framework.easydev.web.swagger.constant.SwaggerConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tac
 * @since 2.0
 */
@ConfigurationProperties(SwaggerConstant.CONFIG_PROP_PREFIX)
public class SwaggerSupportProperties {
    private Boolean enable = true;
    private String basePackage = "cn.tac";
    private String title = "api docs";
    private String version = "SNAPSHOT";
    private Boolean showEnv = true;
    private Boolean showUptime = true;
    private Map<String, ParameterProperties> globalOperationParameters = new HashMap<>();

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getShowEnv() {
        return showEnv;
    }

    public void setShowEnv(Boolean showEnv) {
        this.showEnv = showEnv;
    }

    public Boolean getShowUptime() {
        return showUptime;
    }

    public void setShowUptime(Boolean showUptime) {
        this.showUptime = showUptime;
    }

    public Map<String, ParameterProperties> getGlobalOperationParameters() {
        return globalOperationParameters;
    }

    public void setGlobalOperationParameters(Map<String, ParameterProperties> globalOperationParameters) {
        this.globalOperationParameters = globalOperationParameters;
    }

    public static class ParameterProperties {
        private String modelRef = "string";
        private String parameterType = "query";
        private Boolean required = false;
        private String defaultValue;
        private String description = "global operation parameter";
        private Boolean hidden = false;

        public String getModelRef() {
            return modelRef;
        }

        public void setModelRef(String modelRef) {
            this.modelRef = modelRef;
        }

        public String getParameterType() {
            return parameterType;
        }

        public void setParameterType(String parameterType) {
            this.parameterType = parameterType;
        }

        public Boolean getRequired() {
            return required;
        }

        public void setRequired(Boolean required) {
            this.required = required;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getHidden() {
            return hidden;
        }

        public void setHidden(Boolean hidden) {
            this.hidden = hidden;
        }
    }
}
