package cn.tac.framework.easydev.dao.core.config;

import cn.tac.framework.easydev.dao.core.constant.DaoConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * dao配置
 * @author tac
 * @since 01/11/2017
 */
@ConfigurationProperties(DaoConstant.CONFIG_PROP_PREFIX)
public class DaoCore {
}
