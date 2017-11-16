package cn.tac.framework.easydev.dao.core.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;

/**
 * 感知实体类型
 *
 * @author tac
 * @since 16/11/2017
 */
public interface EntityClassAware<E extends MinEntityStructure> {
    Class<E> getEntityClass();
    E newEntityInstance();
}
