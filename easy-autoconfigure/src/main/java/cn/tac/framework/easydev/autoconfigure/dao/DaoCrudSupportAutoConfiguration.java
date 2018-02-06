package cn.tac.framework.easydev.autoconfigure.dao;

import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author tac
 * @since 2.0
 */
@ConditionalOnClass(DaoCrudSupportProperties.class)
@EnableConfigurationProperties({DaoCrudSupportProperties.class})
public class DaoCrudSupportAutoConfiguration {
}
