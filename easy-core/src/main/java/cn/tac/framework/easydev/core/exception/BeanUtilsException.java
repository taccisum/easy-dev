package cn.tac.framework.easydev.core.exception;

/**
 * @author : tac
 * @since : 02/08/2017
 */
public class BeanUtilsException extends RuntimeException {
    public BeanUtilsException(String method, Exception cause) {
        super("BeanUtils." + method + "()执行异常", cause);
    }
}
