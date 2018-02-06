package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.autoconfigure.web.ExceptionHandlerAutoConfiguration;
import cn.tac.framework.easydev.autoconfigure.web.SwaggerAutoConfiguration;
import cn.tac.framework.easydev.autoconfigure.web.WebMvcConfiguration;
import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.web.core.config.WebCoreProperties;
import cn.tac.framework.easydev.web.swagger.SwaggerDocketFactoryBean;
import cn.tac.framework.easydev.web.swagger.config.SwaggerSupportProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tac
 * @since 2.0
 */
@ConditionalOnClass(WebCoreProperties.class)
@Configuration
@Import({EasyDaoAutoConfiguration.class, ExceptionHandlerAutoConfiguration.class, SwaggerAutoConfiguration.class, WebMvcConfiguration.class})
public class EasyWebAutoConfiguration {
    @Autowired
    private SwaggerSupportProperties swaggerSupportProperties;
    @Autowired
    private EasyCoreProperties easyCoreProperties;

    @Autowired
    private Environment environment;

    @Bean
    public SwaggerDocketFactoryBean swaggerDocketFactoryBean() {
        SwaggerDocketFactoryBean bean = new SwaggerDocketFactoryBean();
        StringBuilder descBuilder = new StringBuilder();
        descBuilder.append("<span style='font-weight:bold'>current version: </span>");
        descBuilder.append(swaggerSupportProperties.getVersion());
        if (swaggerSupportProperties.getShowEnv()) {
            descBuilder.append("<br/>");
            descBuilder.append("<span style='font-weight:bold'>current env: </span>");
            descBuilder.append(Arrays.toString(environment.getActiveProfiles()));
        }
        if (swaggerSupportProperties.getShowUptime()) {
            descBuilder.append("<br/>");
            descBuilder.append("<span style='font-weight:bold'>uptime: </span>");
            descBuilder.append(new SimpleDateFormat(easyCoreProperties.getFormatPattern().getDatetime()).format(new Date()));
        }

        bean.apiInfo(new ApiInfoBuilder()
                .title(swaggerSupportProperties.getTitle())
                .description(descBuilder.toString())
                .version(swaggerSupportProperties.getVersion())
                .build());
        bean.baskPackage(swaggerSupportProperties.getBasePackage());
        return bean;
    }
}
