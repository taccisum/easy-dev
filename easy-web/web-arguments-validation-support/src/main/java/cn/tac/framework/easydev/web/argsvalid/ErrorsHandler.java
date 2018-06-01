package cn.tac.framework.easydev.web.argsvalid;

import org.springframework.validation.Errors;

/**
 * @author tac
 * @since 2.3
 */
public interface ErrorsHandler {
    String extractMsg(Errors errors);
}
