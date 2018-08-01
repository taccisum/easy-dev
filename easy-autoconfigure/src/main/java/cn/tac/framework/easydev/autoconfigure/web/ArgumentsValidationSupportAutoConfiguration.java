package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.core.CommonErrorCode;
import cn.tac.framework.easydev.web.argsvalid.ErrorsHandler;
import cn.tac.framework.easydev.web.argsvalid.handler.MultiErrorsHandler;
import cn.tac.framework.easydev.web.core.builder.RestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tac
 * @since 2.3
 */
@Configuration
@ConditionalOnClass(ErrorsHandler.class)
@RestControllerAdvice
public class ArgumentsValidationSupportAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ErrorsHandler errorsHandler() {
        return new MultiErrorsHandler();
    }

    private ErrorsHandler handler;

    @Autowired
    public void setHandler(ErrorsHandler handler) {
        this.handler = handler;
    }

    @ExceptionHandler(value = BindException.class)
    public RestfulApiResponse bindException(BindException e) {
        return RestfulApiResponseBuilder.failure()
                .code(CommonErrorCode.ARGUMENTS_VALIDATION.getCode())
                .msg(CommonErrorCode.ARGUMENTS_VALIDATION.getMessage())
                .friendlyMsg(handler.extractMsg(e))
                .build();
    }
}
