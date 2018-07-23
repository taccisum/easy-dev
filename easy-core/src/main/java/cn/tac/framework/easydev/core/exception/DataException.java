package cn.tac.framework.easydev.core.exception;

/**
 * @author tac
 * @since 2.3
 */
public class DataException extends RuntimeException {
    public DataException(String reason) {
        super(String.format("error data：%s", reason));
    }
}
