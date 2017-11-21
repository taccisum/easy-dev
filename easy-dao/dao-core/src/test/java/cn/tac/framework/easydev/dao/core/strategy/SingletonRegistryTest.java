package cn.tac.framework.easydev.dao.core.strategy;

import cn.tac.framework.easydev.dao.core.exception.NoSuchInstanceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class SingletonRegistryTest {
    public static SingletonRegistry<MyStrategy> registry;

    @Before
    public void setUp() throws Exception {
        registry = new SingletonRegistry<>();
    }

    @After
    public void tearDown() throws Exception {
        registry = null;
    }

    @Test
    public void registerAndGet() throws Exception {
        registry.register(new Strategy1());
        registry.register(new Strategy1());
        registry.register(new Strategy2());
        assertThat(registry.get(Strategy1.class)).isNotNull();
        assertThat(registry.get(Strategy2.class)).isNotNull();
    }

    @Test(expected = NoSuchInstanceException.class)
    public void getWhenNoRegister() {
        registry.get(Strategy1.class);
    }

    interface MyStrategy {
    }

    static class Strategy1 implements MyStrategy {
    }

    static class Strategy2 implements MyStrategy {
    }
}
