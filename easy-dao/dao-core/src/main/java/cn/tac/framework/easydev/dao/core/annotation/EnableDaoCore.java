package cn.tac.framework.easydev.dao.core.annotation;

import cn.tac.framework.easydev.dao.core.config.DaoCoreBeanConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tac
 * @since 01/11/2017
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DaoCoreBeanConfiguration.class})
public @interface EnableDaoCore {
}
