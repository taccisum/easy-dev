package cn.tac.framework.easydev.dao.core.api;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;

/**
 * 感知通用crud Mapper
 *
 * @author tac
 * @since 2.0
 */
public interface CrudMapperAware<E extends MinEntityStructureAware> {
    CrudMapperSupport<E> getMapper();
}
