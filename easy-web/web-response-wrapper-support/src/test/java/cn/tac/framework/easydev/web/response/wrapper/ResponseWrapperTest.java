package cn.tac.framework.easydev.web.response.wrapper;

import cn.tac.framework.easydev.web.core.builder.RestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.response.wrapper.controller.FooController;
import cn.tac.framework.easydev.web.response.wrapper.util.ResponseWrapperConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author tac
 * @since 11/04/2018
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FooController.class)
public class ResponseWrapperTest {
    @TestConfiguration
    public static class TestConfig implements InitializingBean {
        @Autowired
        private RequestMappingHandlerAdapter adapter;

        @Override
        public void afterPropertiesSet() throws Exception {
            ResponseWrapperConfigurator.insertHandler(
                    adapter,
                    new ResponseWrapperReturnValueHandler(new RequestResponseBodyMethodProcessor(adapter.getMessageConverters())),
                    RequestResponseBodyMethodProcessor.class);
        }
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void testWrapper() throws Exception {
        mvc.perform(get("/foo/test_wrapeer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is(0)))
                .andExpect(jsonPath("$.code", is("0")))
                .andExpect(jsonPath("$.msg", is(RestfulApiResponseBuilder.DEFAULT_SUCCESS_MSG)))
                .andExpect(jsonPath("$.friendlyMsg", is("获取成功")))
                .andExpect(jsonPath("$.data", is("bar")))
                .andExpect(jsonPath("$.stackTrace", is(nullValue())));
    }

    @Test
    public void testWrapperWhenResponseRAR() throws Exception {
        mvc.perform(get("/foo/test_wrapper_when_response_rar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is(0)))
                .andExpect(jsonPath("$.code", is("0")))
                .andExpect(jsonPath("$.msg", is(RestfulApiResponseBuilder.DEFAULT_SUCCESS_MSG)))
                .andExpect(jsonPath("$.friendlyMsg", is("12345")))
                .andExpect(jsonPath("$.data", is(nullValue())))
                .andExpect(jsonPath("$.stackTrace", is(nullValue())));
    }

    @Test
    public void testNormal() throws Exception {
        mvc.perform(get("/foo/test_normal")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("bar1"));
    }

    @Test
    public void testNormalWhenResponseRAR() throws Exception {
        mvc.perform(get("/foo/test_normal_when_response_rar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is(0)))
                .andExpect(jsonPath("$.code", is("0")))
                .andExpect(jsonPath("$.msg", is(RestfulApiResponseBuilder.DEFAULT_SUCCESS_MSG)))
                .andExpect(jsonPath("$.friendlyMsg", is("12345")))
                .andExpect(jsonPath("$.data", is(nullValue())))
                .andExpect(jsonPath("$.stackTrace", is(nullValue())));
    }

    @Test
    public void testVoid() throws Exception {
        mvc.perform(get("/foo/test_void")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is(0)))
                .andExpect(jsonPath("$.code", is("0")))
                .andExpect(jsonPath("$.msg", is(RestfulApiResponseBuilder.DEFAULT_SUCCESS_MSG)))
                .andExpect(jsonPath("$.friendlyMsg", is("执行成功")))
                .andExpect(jsonPath("$.data", is(nullValue())))
                .andExpect(jsonPath("$.stackTrace", is(nullValue())));
    }
}
