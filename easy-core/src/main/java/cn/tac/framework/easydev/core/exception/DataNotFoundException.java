package cn.tac.framework.easydev.core.exception;

/**
 * @author tac
 * @since 2.0
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String dataName) {
        super(String.format("数据不存在：%s", dataName));
    }
}
