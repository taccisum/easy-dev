package cn.tac.framework.easydev.core.exception;

/**
 * EasyDev开发框架的异常基类
 *
 * @author tac
 * @since 2.0
 */
public abstract class EasyDevException extends RuntimeException {
    public EasyDevException() {
        super();
    }

    public EasyDevException(String message) {
        super(message);
    }

    public EasyDevException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasyDevException(Throwable cause) {
        super(cause);
    }
}
