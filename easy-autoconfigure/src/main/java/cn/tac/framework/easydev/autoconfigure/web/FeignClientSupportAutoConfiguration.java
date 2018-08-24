package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.core.exception.BusinessException;
import cn.tac.framework.easydev.core.pojo.ErrorMessage;
import cn.tac.framework.easydev.core.util.ExceptionUtils;
import cn.tac.framework.easydev.web.client.feign.config.FeignClientSupportProperties;
import cn.tac.framework.easydev.web.core.builder.RestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import cn.tac.framework.easydev.web.exception.handler.config.WebExceptionHandlerProperties;
import feign.codec.DecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tac
 * @since 2.3.1
 */
@ConditionalOnClass(FeignClientSupportProperties.class)
@RestControllerAdvice
public class FeignClientSupportAutoConfiguration {
    @Autowired
    private WebExceptionHandlerProperties webExceptionHandlerProperties;

    @ExceptionHandler(value = DecodeException.class)
    public RestfulApiResponse bindException(DecodeException e) {
        int state = RestfulApiResponse.ERROR_STATE;
        Exception ex = e;
        if (e.getCause() instanceof BusinessException) {
            state = RestfulApiResponse.FAILURE_STATE;
            ex = (Exception) e.getCause();
        }
        ErrorMessage message = ExceptionUtils.extractErrorMessage(ex);
        return RestfulApiResponseBuilder.generic(state, message.getCode())
                .data(null)
                .msg(message.getMessage())
                .friendlyMsg(message.getDisplayMessage())
                .stackTrack(getStackTrace(message))
                .build();
    }

    protected String getStackTrace(ErrorMessage message) {
        return webExceptionHandlerProperties.getOutputStackTrace() ? message.getStackTrace() : "check log for details";
    }
}
