package cn.tac.framework.easydev.service.core;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;

/**
 * @author tac
 * @since 17/11/2017
 */
public abstract class ServiceSkeleton<E extends MinEntityStructure<PK>, PK> implements RepositoryAware<E, PK> {
    private RepositorySkeleton repository;

    public ServiceSkeleton(RepositorySkeleton repository) {
        this.repository = repository;
    }

    @Override
    public RepositorySkeleton<E, PK> getRepository() {
        return repository;
    }
}
