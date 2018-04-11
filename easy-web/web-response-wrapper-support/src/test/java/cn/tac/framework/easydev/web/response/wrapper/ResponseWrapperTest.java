package cn.tac.framework.easydev.web.response.wrapper;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testString() throws Exception {
        String responseStr = mvc.perform(get("/foo/bar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseStr);
    }
}
