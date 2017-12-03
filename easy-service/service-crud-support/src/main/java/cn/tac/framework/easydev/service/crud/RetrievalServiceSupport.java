package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.RetrievalRepositorySupport;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;
import cn.tac.framework.easydev.service.crud.api.RetrievalRepositoryAware;
import cn.tac.framework.easydev.service.crud.exception.NoSupportOperationException;

import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public interface RetrievalServiceSupport<E extends MinEntityStructureAware<PK>, PK>
        extends RepositoryAware<E, PK>, RetrievalRepositoryAware<E, PK> {
    default E selectByPrimaryKey(PK pk){
        return getRetrievalRepository().selectByPrimaryKey(pk);
    }

    default List<E> select(E criteria){
        return getRetrievalRepository().select(criteria);
    }

    default List<E> selectAll(){
        return getRetrievalRepository().selectAll();
    }

    @Override
    default RetrievalRepositorySupport<E, PK> getRetrievalRepository(){
        RepositorySkeleton<E, PK> repository = getRepository();
        if (repository instanceof RetrievalRepositorySupport) {
            return ((RetrievalRepositorySupport) repository);
        } else {
            throw new NoSupportOperationException(repository.getClass() + " is not a instance of " + RetrievalRepositorySupport.class);
        }
    }
}
