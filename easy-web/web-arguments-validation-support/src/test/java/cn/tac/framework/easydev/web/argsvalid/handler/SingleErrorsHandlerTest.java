package cn.tac.framework.easydev.web.argsvalid.handler;

import org.junit.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author tac
 * @since 2.3
 */
public class SingleErrorsHandlerTest {
    @Test
    public void extractMsg() throws Exception {
        SingleErrorsHandler handler = spy(new SingleErrorsHandler());
        Errors errors = mock(Errors.class);
        when(errors.hasErrors()).thenReturn(true);
        FieldError fe = new FieldError("name", "field1", "can not be null");
        when(errors.getFieldError()).thenReturn(fe);
        doReturn("message").when(handler).buildMsg(any(), any(), any());
        String msg = handler.extractMsg(errors);
        assertThat(msg).isEqualTo("message");
    }

    @Test
    public void buildMsg() throws Exception {
        SingleErrorsHandler handler = new SingleErrorsHandler();
        String msg = handler.buildMsg("foo", "necessary foo", "can not be null");
        assertThat(msg).contains("foo", "necessary foo", "can not be null");
    }
}
