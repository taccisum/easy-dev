package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.UpdatingRepositorySupport;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;
import cn.tac.framework.easydev.service.crud.api.UpdatingRepositoryAware;
import cn.tac.framework.easydev.service.crud.exception.NoSupportOperationException;

/**
 * @author tac
 * @since 2.0
 */
public interface UpdatingServiceSupport<E extends MinEntityStructureAware<PK>, PK>
        extends RepositoryAware<E, PK>, UpdatingRepositoryAware<E, PK> {
    default E updateByPrimaryKeySelective(E entity) {
        return getUpdatingRepository().updateByPrimaryKeySelective(entity) > 0 ? entity : null;
    }

    @Override
    default UpdatingRepositorySupport<E, PK> getUpdatingRepository() {
        RepositorySkeleton<E, PK> repository = getRepository();
        if (repository instanceof UpdatingRepositorySupport) {
            return ((UpdatingRepositorySupport) repository);
        } else {
            throw new NoSupportOperationException(repository.getClass() + " is not a instance of " + UpdatingRepositorySupport.class);
        }
    }
}
