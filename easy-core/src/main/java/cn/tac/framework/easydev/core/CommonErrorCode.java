package cn.tac.framework.easydev.core;

import cn.tac.framework.easydev.core.pojo.ErrorCode;

/**
 * @author tac
 * @since 2.3
 */
public enum CommonErrorCode implements ErrorCode {
    ARGUMENTS_VALIDATION("100", "happens error when validating arguments"),
    ;

    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage(Object... args) {
        return String.format(message, args);
    }

    CommonErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
