package cn.tac.framework.easydev.service.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.CreationRepositorySupport;

/**
 * @author tac
 * @since 2.0
 */
public interface CreationRepositoryAware<E extends MinEntityStructureAware<PK>, PK> {
    CreationRepositorySupport<E, PK> getCreationRepository();
}
