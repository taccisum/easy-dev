package cn.tac.framework.easydev.core.domain.strategy;

/**
 * 策略工厂清理员，仅用于单元测试
 *
 * @author tac
 * @since 2.3
 */
public class StrategyFactoryCleaner {
    public static void clear() {
        StrategyFactory.registry.clear();
    }

    public static <T extends Strategy> void clear(Class<T> type) {
        StrategyFactory.registry.get(type).clear();
    }
}
