package cn.tac.framework.easydev.core.annotation;

import cn.tac.framework.easydev.core.bean.CoreBeanConfiguration;
import cn.tac.framework.easydev.core.util.SpringUtils;
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
@Import({CoreBeanConfiguration.class, SpringUtils.class})
public @interface EnableEasyDevCore {
}
