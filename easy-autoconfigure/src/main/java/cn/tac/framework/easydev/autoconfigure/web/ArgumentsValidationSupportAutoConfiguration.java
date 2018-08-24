package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.core.exception.biz.ArgumentsValidatingException;
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
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    private ArgumentsValidatingException argumentsValidatingException = new ArgumentsValidatingException("");
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

    /**
     * @since 2.3.1
     */
    @ExceptionHandler(value = BindException.class)
    public RestfulApiResponse bindException(BindException e) {
        return RestfulApiResponseBuilder.failure()
                .code(argumentsValidatingException.getErrorCode().getCode())
                .msg(argumentsValidatingException.getErrorCode().getMessage())
                .friendlyMsg(handler.extractMsg(e))
                .build();
    }

    /**
     * @since 2.3.1
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestfulApiResponse bindException(MethodArgumentNotValidException e) {
        return RestfulApiResponseBuilder.failure()
                .code(argumentsValidatingException.getErrorCode().getCode())
                .msg(argumentsValidatingException.getErrorCode().getMessage())
                .friendlyMsg(handler.extractMsg(e.getBindingResult()))
                .build();
    }
}
