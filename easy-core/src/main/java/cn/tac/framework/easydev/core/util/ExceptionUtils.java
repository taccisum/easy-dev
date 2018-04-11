package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.exception.BusinessException;
import cn.tac.framework.easydev.core.exception.ParameterizedBusinessException;
import cn.tac.framework.easydev.core.pojo.ErrorCode;
import cn.tac.framework.easydev.core.pojo.ErrorMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author tac
 * @since 2.0
 */
public abstract class ExceptionUtils {
    public static ErrorMessage extractErrorMessage(Exception e) {
        ErrorMessage message = new ErrorMessage();
        if (e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;
            message.setCode(be.getErrorCode().getCode());
            if (be instanceof ParameterizedBusinessException) {
                ParameterizedBusinessException pe = (ParameterizedBusinessException) be;
                message.setMessage(pe.getErrorCode().getMessage(pe.getArgs()));
            } else {
                message.setMessage(be.getErrorCode().getMessage());
            }
            message.setDisplayMessage(be.getDisplayMessage());
        } else {
            message.setCode(ErrorCode.SYSTEM_EXCEPTION_CODE);
            //todo::
            message.setMessage("系统异常");
            message.setDisplayMessage(e.getMessage());
        }
        message.setStackTrace(extractStackTrace(e));

        return message;
    }

    public static String extractStackTrace(Throwable e) {
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
