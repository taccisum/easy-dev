package cn.tac.framework.easydev.core.exception;

/**
 * @author tac
 * @since 2.0
 */
public class DataException extends RuntimeException {
    public DataException(String reason) {
        super(String.format("数据异常：%s", reason));
    }
}
