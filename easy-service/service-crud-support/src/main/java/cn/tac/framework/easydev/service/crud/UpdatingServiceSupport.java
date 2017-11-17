package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface UpdatingServiceSupport<E extends MinEntityStructure<PK>, PK> extends RepositoryAware<E, PK> {
    //todo:: updateByPrimaryKeySelective
    E update(E entity);
}