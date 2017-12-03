package cn.tac.framework.easydev.service.core;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;

/**
 * @author tac
 * @since 2.0
 */
public abstract class ServiceSkeleton<E extends MinEntityStructureAware<PK>, PK>
        implements RepositoryAware<E, PK> {
    private RepositorySkeleton repository;

    public ServiceSkeleton(RepositorySkeleton<E, PK> repository) {
        this.repository = repository;
    }

    @Override
    public RepositorySkeleton<E, PK> getRepository() {
        return repository;
    }
}
