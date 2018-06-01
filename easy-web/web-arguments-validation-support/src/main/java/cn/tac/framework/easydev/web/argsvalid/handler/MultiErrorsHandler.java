package cn.tac.framework.easydev.web.argsvalid.handler;

import cn.tac.framework.easydev.web.argsvalid.ErrorsHandler;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * 错误处理器，可以获取到详细的错误情况
 *
 * @author tac
 * @since 2.3
 */
public class MultiErrorsHandler implements ErrorsHandler {
    @Override
    public String extractMsg(Errors errors) {
        if (errors.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fieldError : errors.getFieldErrors()) {
                sb.append("[");
                sb.append(buildMsg(fieldError));
                sb.append("],");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        return "has not errors";
    }

    String buildMsg(FieldError fieldError) {
        return buildMsg(fieldError.getField(), fieldError.getDefaultMessage());
    }

    String buildMsg(String field, String errorMsg) {
        return field + ":" + errorMsg;
    }
}
