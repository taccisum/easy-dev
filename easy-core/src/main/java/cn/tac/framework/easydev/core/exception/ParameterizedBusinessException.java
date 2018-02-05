package cn.tac.framework.easydev.core.exception;

import cn.tac.framework.easydev.core.pojo.ErrorCode;

/**
 * 参数化的通用业务异常基类
 *
 * @author tac
 * @since 1.0
 */
public abstract class ParameterizedBusinessException extends BusinessException {
    private Object[] args;

    public Object[] getArgs() {
        return this.args;
    }

    public ParameterizedBusinessException(ErrorCode errorCode, Object... args) {
        super(errorCode);
        this.args = args;
    }

    public ParameterizedBusinessException(ErrorCode errorCode, String displayMessage, Object... args) {
        super(errorCode, displayMessage);
        this.args = args;
    }
}
