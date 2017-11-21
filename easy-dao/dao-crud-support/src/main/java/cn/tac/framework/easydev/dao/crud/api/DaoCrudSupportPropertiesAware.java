package cn.tac.framework.easydev.dao.crud.api;

import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;

/**
 * 感知配置参数
 *
 * @author tac
 * @since 2.0
 */
public interface DaoCrudSupportPropertiesAware {
    DaoCrudSupportProperties getDaoCrudSupportProperties();
}
