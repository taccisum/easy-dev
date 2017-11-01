package cn.tac.framework.easydev.core.annotation;

import cn.tac.framework.easydev.core.bean.CoreBeanConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用该注解启用EasyDev Core的相关配置
 *
 * @author tac
 * @since 01/11/2017
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(CoreBeanConfiguration.class)
public @interface EnableEasyDevCore {
}
