package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.dao.crud.CreationRepositorySupport;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;
import cn.tac.framework.easydev.service.crud.exception.NoSupportOperationException;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface CreationServiceSupport<E extends MinEntityStructure<PK>, PK> extends RepositoryAware<E, PK> {
    default E insert(E entity) {
        RepositorySkeleton<E, PK> repository = getRepository();
        if (repository instanceof CreationRepositorySupport) {
            return ((CreationRepositorySupport) repository).insert(entity) > 0 ? entity : null;
        } else {
            throw new NoSupportOperationException(repository.getClass() + " is not a instance of " + CreationRepositorySupport.class);
        }
    }
}
