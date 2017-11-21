package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;

/**
 * @author tac
 * @since 2.0
 */
public interface UpdatingServiceSupport<E extends MinEntityStructureAware<PK>, PK> extends RepositoryAware<E, PK> {
    //todo:: updateByPrimaryKeySelective
    E update(E entity);
}
