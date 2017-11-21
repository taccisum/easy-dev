package cn.tac.framework.easydev.service.crud.exception;

import cn.tac.framework.easydev.core.exception.EasyDevException;

/**
 * @author tac
 * @since 2.0
 */
public class NoSupportOperationException extends EasyDevException {
    public NoSupportOperationException(String msg) {
        super(msg);
    }
}
