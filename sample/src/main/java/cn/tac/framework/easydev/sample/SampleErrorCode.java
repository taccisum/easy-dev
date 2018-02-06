package cn.tac.framework.easydev.sample;

import cn.tac.framework.easydev.core.pojo.ErrorCode;

/**
 * @author tac
 * @since 06/02/2018
 */
public enum SampleErrorCode implements ErrorCode {
    FOO_ERROR("101", "业务异常");

    private String message;
    private String code;

    @Override
    public String getMessage(Object... var1) {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }

    SampleErrorCode(String code, String message) {
        this.message = message;
        this.code = code;
    }
}
