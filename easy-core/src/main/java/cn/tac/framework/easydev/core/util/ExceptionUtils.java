package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.exception.BusinessException;
import cn.tac.framework.easydev.core.exception.ParameterizedBusinessException;
import cn.tac.framework.easydev.core.pojo.ErrorMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author tac
 * @since 2.0
 */
public abstract class ExceptionUtils {
    public static ErrorMessage extractErrorMessage(BusinessException exception) {
        ErrorMessage message = new ErrorMessage();
        message.setCode(exception.getErrorCode().getCode());
        if (exception instanceof ParameterizedBusinessException) {
            ParameterizedBusinessException pe = (ParameterizedBusinessException) exception;
            message.setMessage(pe.getErrorCode().getMessage(pe.getArgs()));
        } else {
            message.setMessage(exception.getErrorCode().getMessage());
        }
        message.setDisplayMessage(exception.getDisplayMessage());
        message.setStackTrace(extractStackTrace(exception));

        return message;
    }

    public static String extractStackTrace(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        String stackTrack = sw.toString();
        pw.close();
        try {
            sw.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return stackTrack;
    }
}
