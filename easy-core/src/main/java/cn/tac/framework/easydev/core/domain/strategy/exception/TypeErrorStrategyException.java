package cn.tac.framework.easydev.core.domain.strategy.exception;

/**
 * @author tac
 * @since 2.3
 */
public class TypeErrorStrategyException extends RuntimeException {
    public TypeErrorStrategyException(Class type, Class realType) {
        super(String.format("%s not instance of %s", realType, type));
    }
}
