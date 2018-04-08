package cn.tac.framework.easydev.web.swagger.config;

import cn.tac.framework.easydev.web.swagger.constant.SwaggerConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
}
