package cn.tac.framework.easydev.core.exception.biz;

import cn.tac.framework.easydev.core.CommonErrorCode;
import cn.tac.framework.easydev.core.exception.BusinessException;

/**
 * @author tac
 * @since 2.3
 */
public class ArgumentsValidatingException extends BusinessException {
    public ArgumentsValidatingException(String displayMessage) {
        super(CommonErrorCode.ARGUMENTS_VALIDATION, displayMessage);
    }
}
