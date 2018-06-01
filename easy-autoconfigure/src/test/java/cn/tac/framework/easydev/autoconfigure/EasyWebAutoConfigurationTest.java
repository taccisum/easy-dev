package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.web.argsvalid.ErrorsHandler;
import cn.tac.framework.easydev.web.argsvalid.handler.MultiErrorsHandler;
import cn.tac.framework.easydev.web.messageconverter.config.MessageConverterProperties;
import cn.tac.framework.easydev.web.response.wrapper.config.ResponseWrapperProperties;
import cn.tac.framework.easydev.web.swagger.SwaggerDocketFactoryBean;
import cn.tac.framework.easydev.web.swagger.config.SwaggerSupportProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyWebAutoConfigurationTest.class)
@EnableWebMvc
@SpringBootConfiguration
@ImportAutoConfiguration(EasyWebAutoConfiguration.class)
public class EasyWebAutoConfigurationTest {
    @Autowired private SwaggerSupportProperties swaggerSupportProperties;
    @Autowired private SwaggerDocketFactoryBean swaggerDocketFactoryBean;
    @Autowired private HandlerExceptionResolver handlerExceptionResolver;
    @Autowired private MessageConverterProperties messageConverterProperties;
    @Autowired private ResponseWrapperProperties responseWrapperProperties;
    @Autowired private List<WebMvcConfigurer> webMvcConfigurers;
    @Autowired private ErrorsHandler errorsHandler;

    @Test
    public void testSimply() {
        assertThat(swaggerSupportProperties).isNotNull();
        assertThat(swaggerDocketFactoryBean).isNotNull();
        assertThat(handlerExceptionResolver).isNotNull();
        assertThat(messageConverterProperties).isNotNull();
        assertThat(responseWrapperProperties).isNotNull();
        assertThat(webMvcConfigurers).isNotEmpty();
        assertThat(webMvcConfigurers.size()).isEqualTo(3);
        assertThat(errorsHandler).isNotNull();
        assertThat(errorsHandler).isInstanceOf(MultiErrorsHandler.class);
    }
}
