package cn.tac.framework.easydev.logging.support.logstash;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 2.3
 */
@ConfigurationProperties(prefix = "logging.redis")
public class RedisOutputProperties {
    private String source;
    private String type;
    private String host;
    private Integer port;
    private String key;
    private String password;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
