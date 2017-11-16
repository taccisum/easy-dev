package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;

/**
 * 感知配置参数
 *
 * @author tac
 * @since 16/11/2017
 */
public interface DaoCrudSupportPropertiesAware {
    DaoCrudSupportProperties getDaoCrudSupportProperties();
}
