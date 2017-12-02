package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.DeletionRepositorySupport;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;
import cn.tac.framework.easydev.service.crud.api.DeletionRepositoryAware;
import cn.tac.framework.easydev.service.crud.exception.NoSupportOperationException;

/**
 * @author tac
 * @since 2.0
 */
public interface DeletionServiceSupport<E extends MinEntityStructureAware<PK>, PK>
        extends RepositoryAware<E, PK>, DeletionRepositoryAware<E, PK> {
    default int deleteByPrimaryKey(PK id) {
        return getDeletionRepository().deleteByPrimaryKey(id);
    }

    @Override
    default DeletionRepositorySupport<E, PK> getDeletionRepository() {
        RepositorySkeleton<E, PK> repository = getRepository();
        if (repository instanceof DeletionRepositorySupport) {
            return ((DeletionRepositorySupport) repository);
        } else {
            throw new NoSupportOperationException(repository.getClass() + " is not a instance of " + DeletionRepositorySupport.class);
        }
    }
}
