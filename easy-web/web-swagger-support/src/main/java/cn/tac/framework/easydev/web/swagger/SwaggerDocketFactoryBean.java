package cn.tac.framework.easydev.web.swagger;

import org.springframework.beans.factory.FactoryBean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public class SwaggerDocketFactoryBean implements FactoryBean<Docket> {
    private ApiInfo apiInfo = new ApiInfoBuilder().build();
    private String basePackage = "cn.tac";
    private List<Parameter> parameters = new ArrayList<>();

    @Override
    public Docket getObject() throws Exception {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    @Override
    public Class<?> getObjectType() {
        return Docket.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public SwaggerDocketFactoryBean apiInfo(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
        return this;
    }

    public SwaggerDocketFactoryBean baskPackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

    /**
     * @since 2.3
     */
    public SwaggerDocketFactoryBean globalOperationParameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }
}
