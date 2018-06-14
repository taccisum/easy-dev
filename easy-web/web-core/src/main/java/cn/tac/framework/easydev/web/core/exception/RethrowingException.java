package cn.tac.framework.easydev.web.core.exception;

import cn.tac.framework.easydev.core.exception.BusinessException;
import cn.tac.framework.easydev.core.pojo.ErrorCode;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

/**
 * 将{@link RestfulApiResponse}重新封装为异常抛出
 *
 * @author tac
 * @since 2.3
 */
public class RethrowingException extends BusinessException {
    public RethrowingException(RestfulApiResponse response) {
        super(new ErrorCode() {
            @Override
            public String getMessage(Object... var1) {
                return String.format(response.getMsg(), var1);
            }

            @Override
            public String getCode() {
                return response.getCode();
            }
        }, response.getFriendlyMsg());
    }
}
