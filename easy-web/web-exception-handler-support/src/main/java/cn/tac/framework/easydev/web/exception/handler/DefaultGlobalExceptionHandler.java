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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tac
 * @since 2.0
 */
public class DefaultGlobalExceptionHandler implements ExceptionHandler {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebExceptionHandlerProperties properties;

    public DefaultGlobalExceptionHandler(WebExceptionHandlerProperties properties) {
        this.properties = properties;
    }

    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
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
    }

    protected RestfulApiResponse doProcess(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ErrorMessage message = ExceptionUtils.extractErrorMessage(e);
        RestfulApiResponse resp;
        if (e instanceof BusinessException) {
            resp = RestfulApiResponseBuilder.failure()
                    .code(message.getCode())
                    .msg(message.getMessage())
                    .friendlyMsg(message.getDisplayMessage())
                    .build();
        } else {
            resp = RestfulApiResponseBuilder.error()
                    .stackTrack(getStackTrace(message))
                    .build();
        }
        return resp;
    }

    protected boolean canProcess(Exception ex) {
        return true;
    }

    protected String getStackTrace(ErrorMessage message) {
        return properties.getOutputStackTrace() ? message.getStackTrace() : "详情请查看日志";
    }
}
