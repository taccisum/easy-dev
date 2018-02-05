package cn.tac.framework.easydev.core.pojo;

/**
 * @author tac
 * @since 1.0
 */
public interface AppCode {
    /**
     * 表示成功的状态码
     */
    String SUCCESS_CODE = "0";
    /**
     * 表示系统异常的状态码
     */
    String SYSTEM_EXCEPTION_CODE = "-1";
    /**
     * 表示业务异常的默认状态码
     */
    String DEFAULT_FAILURE_CODE = "1";

    String getMessage(Object... var1);

    String getCode();
}
