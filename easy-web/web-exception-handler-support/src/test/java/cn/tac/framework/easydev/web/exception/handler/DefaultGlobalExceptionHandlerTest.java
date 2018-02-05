package cn.tac.framework.easydev.web.exception.handler;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import cn.tac.framework.easydev.web.exception.handler.config.WebExceptionHandlerProperties;
import cn.tac.framework.easydev.web.exception.handler.controller.FooController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FooController.class)
public class DefaultGlobalExceptionHandlerTest {
    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @TestConfiguration
    public static class MyConfiguration extends WebMvcConfigurerAdapter {
        @Override
        public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
            super.extendHandlerExceptionResolvers(exceptionResolvers);
            ExceptionHandler handler = new DefaultGlobalExceptionHandler(new WebExceptionHandlerProperties());
            exceptionResolvers.add(handler::process);
        }
    }

    @Test
    public void processBusinessException() throws Exception {
        String responseStr = mvc.perform(get("/foo/failure")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        RestfulApiResponse resp = objectMapper.readValue(responseStr, RestfulApiResponse.class);
        assertThat(resp).isNotNull();
        assertThat(resp.getState()).isEqualTo(RestfulApiResponse.FAILURE_STATE);
    }

    @Test
    public void processSystemException() throws Exception {
        String responseStr = mvc.perform(get("/foo/error")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        RestfulApiResponse resp = objectMapper.readValue(responseStr, RestfulApiResponse.class);
        assertThat(resp).isNotNull();
        assertThat(resp.getState()).isEqualTo(RestfulApiResponse.ERROR_STATE);
    }
}
