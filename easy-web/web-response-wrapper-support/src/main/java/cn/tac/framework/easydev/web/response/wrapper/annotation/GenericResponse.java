package cn.tac.framework.easydev.web.response.wrapper.annotation;

import java.lang.annotation.*;

/**
 * @author tac
 * @since 2.2
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenericResponse {
    String msg();

    String type();
}
