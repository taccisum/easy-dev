package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.web.swagger.SwaggerDocketFactoryBean;
import cn.tac.framework.easydev.web.swagger.config.AutoConfigureConditionalClass;
import cn.tac.framework.easydev.web.swagger.config.SwaggerSupportProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tac
 * @since 22/12/2017
 */
@Configuration
@EnableConfigurationProperties({SwaggerSupportProperties.class})
//todo:: 不是SwaggerSupport的AutoConfigureConditionalClass
@ConditionalOnClass(AutoConfigureConditionalClass.class)
//@EnableSwagger2
public class EasyWebAutoConfiguration {
    @Autowired
    private SwaggerSupportProperties swaggerSupportProperties;

    @Bean
    public SwaggerDocketFactoryBean swaggerDocketFactoryBean() {
        SwaggerDocketFactoryBean bean = new SwaggerDocketFactoryBean();
        bean.apiInfo(new ApiInfoBuilder()
                .title(swaggerSupportProperties.getTitle())
                .version(swaggerSupportProperties.getVersion())
                .build());
        bean.baskPackage(swaggerSupportProperties.getBasePackage());
        return bean;
    }
}
