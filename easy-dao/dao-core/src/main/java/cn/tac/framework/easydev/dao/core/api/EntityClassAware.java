package cn.tac.framework.easydev.dao.core.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;

/**
 * 感知实体类型
 *
 * @author tac
 * @since 2.0
 */
public interface EntityClassAware<E extends MinEntityStructureAware> {
    Class<E> getEntityClass();
    E newEntityInstance();
}
