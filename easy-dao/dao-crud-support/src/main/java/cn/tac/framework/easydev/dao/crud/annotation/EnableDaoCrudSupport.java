package cn.tac.framework.easydev.dao.crud.annotation;

import cn.tac.framework.easydev.dao.core.annotation.EnableDaoCore;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudBeanConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tac
 * @since 02/11/2017
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableDaoCore
@Import({DaoCrudBeanConfiguration.class})
public @interface EnableDaoCrudSupport {
}
