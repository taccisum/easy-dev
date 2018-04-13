package cn.tac.framework.easydev.web.response.wrapper.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author tac
 * @since 2.2
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenericResponse {
    @AliasFor("msg")
    String value() default "";

    @AliasFor("value")
    String msg() default "";

    @Deprecated
    String type() default "";
}
