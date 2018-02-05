package cn.tac.framework.easydev.core.exception;

import cn.tac.framework.easydev.core.pojo.ErrorCode;

/**
 * @author tac
 * @since 1.0
 */
public abstract class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private String displayMessage;

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, null);
    }

    /**
     * @param errorCode      异常关联的错误码
     * @param displayMessage 异常对应的友好提示信息，一般面向前端用户
     */
    public BusinessException(ErrorCode errorCode, String displayMessage) {
        this.errorCode = errorCode;
        this.displayMessage = displayMessage;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }
}
