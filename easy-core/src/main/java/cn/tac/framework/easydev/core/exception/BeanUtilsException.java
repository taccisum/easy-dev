package cn.tac.framework.easydev.core.exception;

/**
 * @author tac
 * @since 1.0
 */
public class BeanUtilsException extends RuntimeException {
    public BeanUtilsException(String method, Exception cause) {
        super("exception happened when invoke BeanUtils." + method + "()", cause);
    }
}
