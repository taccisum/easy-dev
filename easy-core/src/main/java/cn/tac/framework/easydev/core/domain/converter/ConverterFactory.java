package cn.tac.framework.easydev.core.domain.converter;

import cn.tac.framework.easydev.core.exception.NoSuchConverterException;
import cn.tac.framework.easydev.core.pojo.KeyValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author tac
 * @since 1.0
 */
public abstract class ConverterFactory {
    private static Logger logger = LoggerFactory.getLogger(ConverterFactory.class);
    private static Map<KeyValuePair<Class<?>, Class<?>>, Converter> converters = new HashMap<>();

    public ConverterFactory() {
    }

    public static void register(Converter converter, Class fromCls, Class toCls) {
        Objects.requireNonNull(converter);
        Objects.requireNonNull(fromCls);
        Objects.requireNonNull(toCls);
        KeyValuePair key = new KeyValuePair<>(fromCls, toCls);
        if (converters.containsKey(key)) {
            logger.warn("redundant converter：from [" + fromCls.toString() + "] to [" + toCls.toString() + "]");
        } else {
            converters.put(key, converter);
        }
    }

    public static <FROM, TO> Converter<FROM, TO> find(Class<FROM> fromCls, Class<TO> toCls) {
        Objects.requireNonNull(fromCls);
        Objects.requireNonNull(toCls);
        KeyValuePair key = new KeyValuePair<>(fromCls, toCls);
        if (!converters.containsKey(key)) {
            throw new NoSuchConverterException(fromCls, toCls);
        } else {
            return converters.get(key);
        }
    }

    public static void clean() {
        converters.clear();
        logger.warn("all converter has been clean. please confirm you really want to do this, because it's may cause some code not work");
    }
}
