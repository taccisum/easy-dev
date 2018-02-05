package cn.tac.framework.easydev.web.exception.handler;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tac
 * @since 2.0
 */
public interface ExceptionHandler {
    ModelAndView process(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e);
}
