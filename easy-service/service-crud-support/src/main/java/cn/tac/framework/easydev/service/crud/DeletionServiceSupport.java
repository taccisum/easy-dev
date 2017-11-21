package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface DeletionServiceSupport<E extends MinEntityStructureAware<PK>, PK> extends RepositoryAware<E, PK> {
    int delete(PK id);
}
