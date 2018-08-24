package cn.tac.framework.easydev.logging.support.logstash.appender.redis;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.cwbase.logback.AdditionalField;
import com.cwbase.logback.JSONEventLayout;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Arrays;
import java.util.Iterator;

/**
 * this class is modified from {@link com.cwbase.logback.RedisAppender}
 *
 * @author tac
 * @since 22/08/2018
 */
public class CustomRedisAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    JedisPool pool;
    JSONEventLayout jsonlayout = new JSONEventLayout();
    Layout<ILoggingEvent> layout;
    String host = "localhost";
    int port = 6379;
    String key = null;
    int timeout = 2000;
    String password = null;
    int database = 0;
    boolean enabled = true;

    public CustomRedisAppender() {
    }

    protected void append(ILoggingEvent event) {
        if (!enabled) {
            this.addInfo(this.name + " is disabled");
            return;
        }
        Jedis client;
        try {
            client = this.pool.getResource();
        } catch (JedisConnectionException e) {
            // wrap JedisConnectionException to provide more error info
            throw wrap(e);
        }

        try {
            String json = this.layout == null ? this.jsonlayout.doLayout(event) : this.layout.doLayout(event);
            client.rpush(this.key, new String[]{json});
        } catch (Exception var7) {
            var7.printStackTrace();
            this.pool.returnBrokenResource(client);
            client = null;
        } finally {
            if (client != null) {
                this.pool.returnResource(client);
            }

        }
    }

    private JedisConnectionException wrap(JedisConnectionException e) {
        return new JedisConnectionException(e) {
            @Override
            public String getMessage() {
                return super.getMessage() + "[" + String.format("host: %s, port: %d, timeout: %d, db: %d", host, port, timeout, database) + "]";
            }
        };
    }

    /**
     * @deprecated
     */
    @Deprecated
    public String getSource() {
        return this.jsonlayout.getSource();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setSource(String source) {
        this.jsonlayout.setSource(source);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public String getSourceHost() {
        return this.jsonlayout.getSourceHost();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setSourceHost(String sourceHost) {
        this.jsonlayout.setSourceHost(sourceHost);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public String getSourcePath() {
        return this.jsonlayout.getSourcePath();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setSourcePath(String sourcePath) {
        this.jsonlayout.setSourcePath(sourcePath);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public String getTags() {
        if (this.jsonlayout.getTags() != null) {
            Iterator<String> i = this.jsonlayout.getTags().iterator();
            StringBuilder sb = new StringBuilder();

            while (i.hasNext()) {
                sb.append((String) i.next());
                if (i.hasNext()) {
                    sb.append(',');
                }
            }

            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setTags(String tags) {
        if (tags != null) {
            String[] atags = tags.split(",");
            this.jsonlayout.setTags(Arrays.asList(atags));
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public String getType() {
        return this.jsonlayout.getType();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setType(String type) {
        this.jsonlayout.setType(type);
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setMdc(boolean flag) {
        this.jsonlayout.setProperties(flag);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public boolean getMdc() {
        return this.jsonlayout.getProperties();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setLocation(boolean flag) {
        this.jsonlayout.setLocationInfo(flag);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public boolean getLocation() {
        return this.jsonlayout.getLocationInfo();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setCallerStackIndex(int index) {
        this.jsonlayout.setCallerStackIdx(index);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public int getCallerStackIndex() {
        return this.jsonlayout.getCallerStackIdx();
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void addAdditionalField(AdditionalField p) {
        this.jsonlayout.addAdditionalField(p);
    }

    public Layout<ILoggingEvent> getLayout() {
        return this.layout;
    }

    public void setLayout(Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }

    public void start() {
        this.addInfo("init jedis pool for Appender[" + this.name + "]");
        super.start();
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setTestOnBorrow(true);
        this.pool = new JedisPool(config, this.host, this.port, this.timeout, nullIfEmpty(this.password), this.database);
    }

    public void stop() {
        super.stop();
        this.pool.destroy();
    }

    private String nullIfEmpty(String str) {
        return StringUtils.isEmpty(this.password) ? null : str;
    }
}

