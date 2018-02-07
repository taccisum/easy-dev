package cn.tac.framework.easydev.web.messageconverter;

import cn.tac.framework.easydev.web.messageconverter.controller.FooController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
public class MessageConverterConfiguratorTest {
    @Autowired
    private MockMvc mvc;

    @TestConfiguration
    public static class MvcConfiguration extends WebMvcConfigurerAdapter {
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            super.extendMessageConverters(converters);
            logger.debug("extend message converter");
            MessageConverterConfigurator configurator = new MessageConverterConfigurator();
            configurator.setObjectMapperBuilder(new GenericObjectMapperBuilder().dateFormatPattern("yyyy-MM-dd HH:mm:ss").long2String(true));
            configurator.configureMessageConverters(converters);
        }
    }

    @Test
    public void extendMessageConverters() throws Exception {
        String responseStr = mvc.perform(get("/foo/index")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        assertThat(responseStr).contains("\"157928765366870016\"", "2017-01-01 12:34:56");
    }
}
