package cn.tac.framework.easydev.core.domain.converter.annotation;

import java.lang.annotation.*;

/**
 * @author tac
 * @since 2.0
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Register2Factory {
    boolean register() default true;

    Class from();

    Class to();
}
