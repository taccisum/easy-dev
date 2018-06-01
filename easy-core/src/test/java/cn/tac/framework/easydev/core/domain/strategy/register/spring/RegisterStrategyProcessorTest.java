package cn.tac.framework.easydev.core.domain.strategy.register.spring;

import cn.tac.framework.easydev.core.domain.strategy.Strategy;
import cn.tac.framework.easydev.core.domain.strategy.StrategyFactory;
import cn.tac.framework.easydev.core.domain.strategy.StrategyFactoryCleaner;
import cn.tac.framework.easydev.core.domain.strategy.annotation.RegisterStrategy;
import cn.tac.framework.easydev.core.domain.strategy.exception.ExistsStrategyException;
import cn.tac.framework.easydev.core.domain.strategy.exception.TypeErrorStrategyException;
import org.junit.After;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.3
 */
public class RegisterStrategyProcessorTest {
    @After
    public void tearDown() throws Exception {
        StrategyFactoryCleaner.clear();
    }

    @Test
    public void postProcessAfterInitialization() throws Exception {
        new RegisterStrategyProcessor().postProcessAfterInitialization(new SayHelloWorldStrategy(), "s");
        FooStrategy strategy = StrategyFactory.get(FooStrategy.class, "SayHelloWorldStrategy");
        assertThat(strategy).isNotNull();
        assertThat(strategy.greeting()).isEqualTo("hello world");
    }

    @Test(expected = TypeErrorStrategyException.class)
    public void registerErrorTypeStrategy() throws Exception {
        new RegisterStrategyProcessor().postProcessAfterInitialization(new ErrorTypeStrategy(), "s");
    }

    @Test(expected = ExistsStrategyException.class)
    public void registerRepeatedKeyStrategy() throws Exception {
        RegisterStrategyProcessor processor = new RegisterStrategyProcessor();
        processor.postProcessAfterInitialization(new RepeatedKeyStrategy(), "s");
        processor.postProcessAfterInitialization(new RepeatedKeyStrategy(), "s");
    }

    @Test
    public void registerRepeatedKeyStrategyButDifferentType() throws Exception {
        new RegisterStrategyProcessor().postProcessAfterInitialization(new RepeatedKeyStrategy(), "s");
        new RegisterStrategyProcessor().postProcessAfterInitialization(new AnotherRepeatedKeyStrategy(), "s");
        FooStrategy s1 = StrategyFactory.get(FooStrategy.class, "RepeatedKeyStrategy");
        BarStrategy s2 = StrategyFactory.get(BarStrategy.class, "RepeatedKeyStrategy");
        assertThat(s1).isNotNull();
        assertThat(s2).isNotNull();
    }

    @Test
    public void registerWhenNoStrategy() throws Exception {
        new RegisterStrategyProcessor().postProcessAfterInitialization(new NoStrategy(), "s");
        FooStrategy strategy = StrategyFactory.get(FooStrategy.class, "NoStrategy");
        assertThat(strategy).isNull();
    }

    @Test
    public void withoutRegisterStrategy() throws Exception {
        RegisterStrategyProcessor processor = new RegisterStrategyProcessor();
        processor.postProcessAfterInitialization(new WithoutRegisterStrategy(), "s");
        processor.postProcessAfterInitialization(new WithoutRegisterStrategy1(), "s");
        FooStrategy s1 = StrategyFactory.get(FooStrategy.class, "WithoutRegisterStrategy");
        BarStrategy s2 = StrategyFactory.get(BarStrategy.class, "WithoutRegisterStrategy1");
        assertThat(s1).isNull();
        assertThat(s2).isNull();
    }

    private interface FooStrategy extends Strategy {
        String greeting();
    }

    private interface BarStrategy extends Strategy {
    }

    @RegisterStrategy(type = FooStrategy.class, key = "SayHelloWorldStrategy")
    private static class SayHelloWorldStrategy implements FooStrategy {
        @Override
        public String greeting() {
            return "hello world";
        }
    }

    @RegisterStrategy(type = BarStrategy.class, key = "ErrorTypeStrategy")
    private static class ErrorTypeStrategy implements FooStrategy {
        @Override
        public String greeting() {
            return "hello world";
        }
    }

    @RegisterStrategy(type = FooStrategy.class, key = "RepeatedKeyStrategy")
    private static class RepeatedKeyStrategy implements FooStrategy {
        @Override
        public String greeting() {
            return "hello world";
        }
    }

    @RegisterStrategy(type = BarStrategy.class, key = "RepeatedKeyStrategy")
    private static class AnotherRepeatedKeyStrategy implements BarStrategy {
    }

    @RegisterStrategy(type = FooStrategy.class, key = "NoStrategy")
    private static class NoStrategy {
    }

    @RegisterStrategy(type = BarStrategy.class, key = "WithoutRegisterStrategy", register = false)
    private static class WithoutRegisterStrategy implements BarStrategy {
    }

    private static class WithoutRegisterStrategy1 implements BarStrategy {
    }
}
