package cn.tac.framework.easydev.core.exception;

/**
 * @author tac
 * @since 1.0
 */
public class NoSuchConverterException extends RuntimeException {
    public NoSuchConverterException(Class fromCls, Class toCls) {
        super("does not exist converter: from [" + fromCls + "] to [" + toCls + "]");
    }
}
