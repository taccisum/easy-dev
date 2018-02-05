package cn.tac.framework.easydev.web.exception.handler.exception;

import cn.tac.framework.easydev.core.exception.BusinessException;
import cn.tac.framework.easydev.core.pojo.ErrorCode;

/**
 * @author tac
 * @since 2.0
 */
public class FooException extends BusinessException {
    public FooException(String displayMessage) {
        super(new ErrorCode() {
            @Override
            public String getMessage(Object... var1) {
                return "业务异常";
            }

            @Override
            public String getCode() {
                return "233";
            }
        }, displayMessage);
    }
}
