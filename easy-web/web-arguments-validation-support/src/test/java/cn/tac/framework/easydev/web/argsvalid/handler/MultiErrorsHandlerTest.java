package cn.tac.framework.easydev.web.argsvalid.handler;

import org.junit.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author tac
 * @since 2.3
 */
public class MultiErrorsHandlerTest {
    @Test
    public void extractMsg() throws Exception {
        MultiErrorsHandler handler = spy(new MultiErrorsHandler());
        Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(true);
        ArrayList<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("", "", ""));
        fieldErrors.add(new FieldError("", "", ""));
        fieldErrors.add(new FieldError("", "", ""));
        when(errors.getFieldErrors()).thenReturn(fieldErrors);
        doReturn("fe").when(handler).buildMsg(any());
        String msg = handler.extractMsg(errors);
        assertThat(msg).isEqualTo("[fe],[fe],[fe]");
    }

    @Test
    public void buildMsg() throws Exception {
        MultiErrorsHandler handler = new MultiErrorsHandler();
        String msg = handler.buildMsg("foo", "can not be null");
        assertThat(msg).contains("foo:can not be null");
    }
}
