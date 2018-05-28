package cn.tac.framework.easydev.web.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
@SpringBootApplication
@RestController
@EnableSwagger2
@Api(value = "test for swagger support")
public class SwaggerApplication4Test {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication4Test.class, args);
    }

    @Bean
    public SwaggerDocketFactoryBean swaggerDocketFactoryBean(){
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder().name("Authorization").modelRef(new ModelRef("string")).defaultValue("Bearer {token}").parameterType("header").build());
        return new SwaggerDocketFactoryBean().baskPackage("cn.tac.framework.easydev.web.swagger").globalOperationParameters(parameters);
    }

    @GetMapping("greeting")
    @ApiOperation("你好啊")
    public String greeting(@RequestParam @ApiParam("姓名") String name) {
        return "hello " + name;
    }
}
