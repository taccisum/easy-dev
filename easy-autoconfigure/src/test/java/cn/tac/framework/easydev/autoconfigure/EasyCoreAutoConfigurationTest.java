package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.autoconfigure.converter.String2IntegerConverter;
import cn.tac.framework.easydev.autoconfigure.strategy.PrintStrategy;
import cn.tac.framework.easydev.autoconfigure.strategy.SayHelloWorldStrategy;
import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.core.domain.converter.Converter;
import cn.tac.framework.easydev.core.domain.converter.ConverterFactory;
import cn.tac.framework.easydev.core.domain.converter.register.spring.RegisterConverterProcessor;
import cn.tac.framework.easydev.core.domain.strategy.StrategyFactory;
import cn.tac.framework.easydev.core.domain.strategy.register.spring.RegisterStrategyProcessor;
import cn.tac.framework.easydev.core.util.SpringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
@SpringBootTest(classes = EasyCoreAutoConfigurationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@SpringBootConfiguration
@ImportAutoConfiguration(EasyCoreAutoConfiguration.class)
@Import({String2IntegerConverter.class, SayHelloWorldStrategy.class})
@RunWith(SpringRunner.class)
public class EasyCoreAutoConfigurationTest {
    @Autowired private EasyCoreProperties easyCoreProperties;
    @Autowired private SpringUtils springUtils;
    @Autowired private RegisterConverterProcessor registerConverterProcessor;
    @Autowired private RegisterStrategyProcessor registerStrategyProcessor;

    @Test
    public void testSimply() {
        Assert.assertNotNull(easyCoreProperties);
        Assert.assertNotNull(springUtils);
        Assert.assertNotNull(SpringUtils.getBean(EasyCoreProperties.class));
        Assert.assertNotNull(SpringUtils.getBean(EasyCoreProperties.class));
        Assert.assertNotNull(registerConverterProcessor);
    }

    @Value("${converter.string2int.increase}")
    private Integer increase;

    @Test
    public void testConverterRegister() {
        Converter<String, Integer> converter = ConverterFactory.find(String.class, Integer.class);
        assertThat(converter).isInstanceOf(String2IntegerConverter.class);
        assertThat(converter).isNotNull();
        assertThat(converter.convert("123")).isEqualTo(123 + increase);
    }

    @Test
    public void testStrategyRegister() {
        PrintStrategy strategy = StrategyFactory.get(PrintStrategy.class, "SAY_HELLO_WORLD");
        assertThat(strategy).isNotNull();
        assertThat(strategy.greeting()).isEqualTo("hello world");
    }
}
