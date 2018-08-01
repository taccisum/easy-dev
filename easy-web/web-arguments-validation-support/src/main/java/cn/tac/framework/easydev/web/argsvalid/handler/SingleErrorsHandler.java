package cn.tac.framework.easydev.web.argsvalid.handler;

import cn.tac.framework.easydev.web.argsvalid.ErrorsHandler;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * 简单的错误处理器，只处理第一个错误
 *
 * @author tac
 * @since 2.3
 */
public class SingleErrorsHandler implements ErrorsHandler {
    @Override
    public String extractMsg(Errors errors) {
        if(errors.hasErrors()){
            FieldError fieldError = errors.getFieldError();
            return buildMsg(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
        }
        return "has not errors";
    }

    String buildMsg(String field, Object rejectedValue, String errorMsg) {
        StringBuilder sb = new StringBuilder();
        sb.append(field).append("'s value (\"").append(rejectedValue == null ? "null" : rejectedValue).append("\")").append("is invalid");
        if (!StringUtils.isEmpty(errorMsg)) {
            sb.append("：").append(errorMsg);
        }
        return sb.toString();
    }
}
