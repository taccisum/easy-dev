package cn.tac.framework.easydev.service.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.UpdatingRepositorySupport;

/**
 * @author tac
 * @since 2.0
 */
public interface UpdatingRepositoryAware<E extends MinEntityStructureAware<PK>, PK> {
    UpdatingRepositorySupport<E, PK> getUpdatingRepository();
}
