package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.exception.BusinessException;
import cn.tac.framework.easydev.core.exception.ParameterizedBusinessException;
import cn.tac.framework.easydev.core.pojo.ErrorCode;
import cn.tac.framework.easydev.core.pojo.ErrorMessage;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class ExceptionUtilsTest {
    @Rule
    public OutputCapture capture = new OutputCapture();

    @After
    public void tearDown() throws Exception {
        capture.reset();
    }

    @Test
    public void extractErrorMessage() throws Exception {
        ErrorMessage message = ExceptionUtils.extractErrorMessage(new FooException());
        assertThat(message.getCode()).isEqualTo("233");
        assertThat(message.getMessage()).isEqualTo("foo exception");
        assertThat(message.getDisplayMessage()).isEqualTo("display message");
        System.err.println(message.getStackTrace());
        assertThat(capture.toString()).contains("FooException");
    }

    @Test
    public void extractErrorMessageFromParamerterizedBizException() throws Exception {
        ErrorMessage message = ExceptionUtils.extractErrorMessage(new ParameterizedFooException("error reason"));
        assertThat(message.getCode()).isEqualTo("234");
        assertThat(message.getMessage()).isEqualTo("parameterized foo exception: error reason");
        assertThat(message.getDisplayMessage()).isEqualTo("display message");
        System.err.println(message.getStackTrace());
        assertThat(capture.toString()).contains("ParameterizedFooException");
    }

    @Test
    public void extractStackTrace() throws Exception {
        System.err.println(ExceptionUtils.extractStackTrace(new IllegalArgumentException("extract stack track test")));
        assertThat(capture.toString()).contains("IllegalArgumentException", "extract stack track test");
    }

    private class FooException extends BusinessException {
        public FooException() {
            super(new ErrorCode() {
                @Override
                public String getMessage(Object... var1) {
                    return "foo exception";
                }

                @Override
                public String getCode() {
                    return "233";
                }
            }, "display message");
        }
    }

    private class ParameterizedFooException extends ParameterizedBusinessException {
        public ParameterizedFooException(String reason) {
            super(new ErrorCode() {
                @Override
                public String getMessage(Object... var1) {
                    return String.format("parameterized foo exception: %s", var1);
                }

                @Override
                public String getCode() {
                    return "234";
                }
            }, "display message", reason);
        }
    }
}
