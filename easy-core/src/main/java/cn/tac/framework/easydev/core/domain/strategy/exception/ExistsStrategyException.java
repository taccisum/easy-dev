package cn.tac.framework.easydev.core.domain.strategy.exception;

/**
 * @author tac
 * @since 2.3
 */
public class ExistsStrategyException extends RuntimeException {
    public ExistsStrategyException(String key, Class clazz) {
        super(String.format("exists strategy: %s[%s]", key, clazz));
    }
}
