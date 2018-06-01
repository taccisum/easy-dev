package cn.tac.framework.easydev.core.domain.strategy;

import cn.tac.framework.easydev.core.domain.strategy.exception.ExistsStrategyException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tac
 * @since 2.3
 */
public abstract class StrategyFactory {
    /**
     * 策略注册表
     */
    static Map<Class<? extends Strategy>, Map<String, Strategy>> registry = new HashMap<>();

    public static <T extends Strategy> T get(Class<T> type, String key) {
        Map<String, Strategy> map = registry.get(type);
        if (map == null) {
            return null;
        }
        return (T) map.get(key);
    }

    /**
     * @throws ExistsStrategyException 已存在的策略key
     */
    public static <T extends Strategy> void register(Class<? extends T> type, String key, T strategy) throws ExistsStrategyException {
        Map<String, Strategy> map = registry.get(type);
        if (map == null) {
            map = new HashMap<>();
            registry.put(type, map);
        } else {
            if (map.containsKey(key)) {
                throw new ExistsStrategyException(key, strategy.getClass());
            }
        }
        map.put(key, strategy);
    }
}
