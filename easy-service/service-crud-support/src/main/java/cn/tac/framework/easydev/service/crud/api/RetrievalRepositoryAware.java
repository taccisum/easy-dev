package cn.tac.framework.easydev.service.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.RetrievalRepositorySupport;

/**
 * @author tac
 * @since 2.0
 */
public interface RetrievalRepositoryAware<E extends MinEntityStructureAware<PK>, PK> {
    RetrievalRepositorySupport<E, PK> getRetrievalRepository();
}
