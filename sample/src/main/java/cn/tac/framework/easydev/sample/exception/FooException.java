package cn.tac.framework.easydev.sample.exception;

import cn.tac.framework.easydev.core.exception.BusinessException;
import cn.tac.framework.easydev.sample.SampleErrorCode;

/**
 * @author tac
 * @since 06/02/2018
 */
public class FooException extends BusinessException {
    public FooException(String displayMessage) {
        super(SampleErrorCode.FOO_ERROR, displayMessage);
    }
}
