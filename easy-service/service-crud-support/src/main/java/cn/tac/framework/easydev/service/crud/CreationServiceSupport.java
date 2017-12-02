package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.CreationRepositorySupport;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;
import cn.tac.framework.easydev.service.crud.api.CreationRepositoryAware;
import cn.tac.framework.easydev.service.crud.exception.NoSupportOperationException;

/**
 * @author tac
 * @since 2.0
 */
public interface CreationServiceSupport<E extends MinEntityStructureAware<PK>, PK>
        extends RepositoryAware<E, PK>, CreationRepositoryAware<E, PK> {
    default E insert(E entity) {
        return getCreationRepository().insert(entity) > 0 ? entity : null;
    }

    default E insertSelective(E entity) {
        return getCreationRepository().insertSelective(entity) > 0 ? entity : null;
    }

    @Override
    default CreationRepositorySupport<E, PK> getCreationRepository() {
        RepositorySkeleton<E, PK> repository = getRepository();
        if (repository instanceof CreationRepositorySupport) {
            return ((CreationRepositorySupport) repository);
        } else {
            throw new NoSupportOperationException(repository.getClass() + " is not a instance of " + CreationRepositorySupport.class);
        }
    }
}
