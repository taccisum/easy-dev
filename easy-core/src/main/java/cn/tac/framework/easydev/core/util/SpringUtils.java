package cn.tac.framework.easydev.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * 提供与Spring相关的常用工具
 * @author tac
 * @since 1.0
 */
public class SpringUtils implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(SpringUtils.class);
    public static String applicationName = null;
    public static String applicationAbbr = null;

    private static ApplicationContext ctx = null;

    public static Object getBean(String beanName) {
        if (ctx != null) {
            try {
                return ctx.getBean(beanName);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        T result = null;
        if (ctx != null) {
            try {
                result = ctx.getBean(beanName, requiredType);
            } catch (Exception e) {
                logger.error("getBean() error", e);
            }
        }

        return result;
    }

    public static <T> T getBean(Class<T> requiredType) {
        T result = null;
        if (ctx != null) {
            try {
                result = ctx.getBean(requiredType);
            } catch (Exception e) {
                logger.error("getBean() error", e);
            }
        }

        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationName = applicationContext.getId();
        ctx = applicationContext;
        applicationAbbr = getProperty("spring.application.abbr", applicationName);
    }

    public static String getProperty(String key, String defaultValue) {
        String result = defaultValue;
        Environment environment = getBean(Environment.class);
        if (environment != null) {
            result = environment.getProperty(key, defaultValue);
        }

        return result;
    }
}
