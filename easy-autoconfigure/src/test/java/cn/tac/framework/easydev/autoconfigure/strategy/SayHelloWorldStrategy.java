package cn.tac.framework.easydev.autoconfigure.strategy;

import cn.tac.framework.easydev.core.domain.strategy.annotation.RegisterStrategy;

/**
 * @author tac
 * @since 2.3
 */
@RegisterStrategy(type = PrintStrategy.class, key = "SAY_HELLO_WORLD")
public class SayHelloWorldStrategy implements PrintStrategy {
    @Override
    public String greeting() {
        return "hello world";
    }
}
