package cn.tac.framework.easydev.service.core.api;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface RepositoryAware<E extends MinEntityStructure<PK>, PK> {
    RepositorySkeleton<E, PK> getRepository();
}
