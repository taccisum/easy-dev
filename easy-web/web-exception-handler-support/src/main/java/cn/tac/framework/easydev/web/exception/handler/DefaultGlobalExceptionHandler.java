package cn.tac.framework.easydev.web.exception.handler;

import cn.tac.framework.easydev.core.exception.BusinessException;
import cn.tac.framework.easydev.core.pojo.ErrorMessage;
import cn.tac.framework.easydev.core.util.ExceptionUtils;
import cn.tac.framework.easydev.web.core.builder.RestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import cn.tac.framework.easydev.web.core.utils.WebUtils;
import cn.tac.framework.easydev.web.exception.handler.config.WebExceptionHandlerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tac
 * @since 2.0
 */
public class DefaultGlobalExceptionHandler implements HandlerExceptionResolver {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebExceptionHandlerProperties properties;

    public DefaultGlobalExceptionHandler(WebExceptionHandlerProperties properties) {
        this.properties = properties;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        if (isAjax(request)) {
            if (!canProcess(e)) {
                throw new UnsupportedOperationException("process exception " + e.getClass());
            }

            RestfulApiResponse result;
            result = doProcess(request, response, handler, e);

            try {
                WebUtils.writeJson(response, result);
            } catch (IOException ioe) {
                logger.error("http响应流写入异常", ioe);
            }

            return new ModelAndView();      //这里要返回一个空的model and view
        } else {
            return new ModelAndView(properties.getDefaultView());
        }
    }

    protected RestfulApiResponse doProcess(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ErrorMessage message = ExceptionUtils.extractErrorMessage(e);
        RestfulApiResponse resp;
        if (e instanceof BusinessException) {
            logger.debug(message.getStackTrace());
            resp = RestfulApiResponseBuilder.failure()
                    .code(message.getCode())
                    .msg(message.getMessage())
                    .friendlyMsg(message.getDisplayMessage())
                    .stackTrace(getStackTrace(message))
                    .build();
        } else {
            logger.error(message.getStackTrace());
            resp = RestfulApiResponseBuilder.error()
                    .stackTrace(getStackTrace(message))
                    .build();
        }
        return resp;
    }

    protected boolean isAjax(HttpServletRequest request) {
        //todo::
        return true;
//        return WebUtils.isAjax(request);
    }

    protected boolean canProcess(Exception ex) {
        return true;
    }

    protected String getStackTrace(ErrorMessage message) {
        return properties.getOutputStackTrace() ? message.getStackTrace() : "check log for details";
    }
}
