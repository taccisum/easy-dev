package cn.tac.framework.easydev.autoconfigure.web;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.web.swagger.SwaggerDocketFactoryBean;
import cn.tac.framework.easydev.web.swagger.config.SwaggerSupportProperties;
import cn.tac.framework.easydev.web.swagger.constant.SwaggerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tac
 * @since 2.0
 */
@Configuration
@ConditionalOnClass(SwaggerSupportProperties.class)
@ConditionalOnProperty(name = SwaggerConstant.CONFIG_PROP_PREFIX + ".enable", havingValue = "true", matchIfMissing = true)
@EnableSwagger2
@EnableConfigurationProperties({SwaggerSupportProperties.class})
public class SwaggerAutoConfiguration extends WebMvcConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SwaggerSupportProperties swaggerSupportProperties;
    @Autowired
    private EasyCoreProperties easyCoreProperties;

    @Autowired
    private Environment environment;

    @Bean
    @ConditionalOnMissingBean
    public SwaggerDocketFactoryBean swaggerDocketFactoryBean() {
        if (logger.isInfoEnabled()) {
            logger.info("配置swagger docket factory bean，使用框架提供的默认配置");
        }
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
        Map<String, SwaggerSupportProperties.ParameterProperties> globalOperationParameters = swaggerSupportProperties.getGlobalOperationParameters();
        if (globalOperationParameters != null && globalOperationParameters.size() > 0) {
            List<Parameter> parameters = new ArrayList<>();
            for (String name : globalOperationParameters.keySet()) {
                SwaggerSupportProperties.ParameterProperties parameterProperties = globalOperationParameters.get("name");
                Parameter parameter = new ParameterBuilder()
                        .name(name)
                        .modelRef(new ModelRef(parameterProperties.getModelRef()))
                        .parameterType(parameterProperties.getParameterType())
                        .required(parameterProperties.getRequired())
                        .defaultValue(parameterProperties.getDefaultValue())
                        .description(parameterProperties.getDescription())
                        .hidden(parameterProperties.getHidden())
                        .build();
                parameters.add(parameter);
            }

            bean.globalOperationParameters(parameters);
        }
        return bean;
    }
}
