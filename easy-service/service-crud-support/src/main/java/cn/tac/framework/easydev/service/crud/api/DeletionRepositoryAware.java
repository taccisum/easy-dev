package cn.tac.framework.easydev.service.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.DeletionRepositorySupport;

/**
 * @author tac
 * @since 2.0
 */
public interface DeletionRepositoryAware<E extends MinEntityStructureAware<PK>, PK> {
    DeletionRepositorySupport<E, PK> getDeletionRepository();
}
