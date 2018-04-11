package cn.tac.framework.easydev.web.response.wrapper.util;

import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 11/04/2018
 */
public class ResponseWrapperConfiguratorTest {
    @Test
    public void insertHandler() throws Exception {
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
        handlers.add(new ModelAndViewMethodReturnValueHandler());
        ArrayList<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new StringHttpMessageConverter());
        handlers.add(new RequestResponseBodyMethodProcessor(converters));
        handlers.add(new HttpHeadersReturnValueHandler());

        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        adapter.setReturnValueHandlers(handlers);
        ResponseWrapperConfigurator.insertHandler(adapter, new TestHandler(), RequestResponseBodyMethodProcessor.class);
        assertThat(adapter.getReturnValueHandlers().size()).isEqualTo(4);
        assertThat(adapter.getReturnValueHandlers().get(1)).isInstanceOf(TestHandler.class);
        assertThat(adapter.getReturnValueHandlers().get(2)).isInstanceOf(RequestResponseBodyMethodProcessor.class);
    }

    private class TestHandler implements HandlerMethodReturnValueHandler {
        @Override
        public boolean supportsReturnType(MethodParameter methodParameter) {
            return false;
        }

        @Override
        public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {

        }
    }
}
