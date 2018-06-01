package cn.tac.framework.easydev.core.domain.strategy.annotation;

import cn.tac.framework.easydev.core.domain.strategy.Strategy;

import java.lang.annotation.*;

/**
 * @author tac
 * @since 2.3
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterStrategy {
    boolean register() default true;

    Class<? extends Strategy> type();

    String key();
}
