package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.*;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;

/**
 * 通用的支持CRUD操作的Service
 *
 * @author tac
 * @since 2.0
 */
public abstract class CrudServiceSupport<E extends MinEntityStructureAware<PK>, PK> extends ServiceSkeleton<E, PK>
        implements
        CreationServiceSupport<E, PK>,
        DeletionServiceSupport<E, PK>,
        RetrievalServiceSupport<E, PK>,
        UpdatingServiceSupport<E, PK> {
    public CrudServiceSupport(CrudRepositorySupport<E, PK> repository) {
        super(repository);
    }

    @Override
    public CreationRepositorySupport<E, PK> getCreationRepository() {
        //直接转换，避免类型检查造成的开销
        return getRepository();
    }

    @Override
    public DeletionRepositorySupport<E, PK> getDeletionRepository() {
        //直接转换，避免类型检查造成的开销
        return getRepository();
    }

    @Override
    public RetrievalRepositorySupport<E, PK> getRetrievalRepository() {
        //直接转换，避免类型检查造成的开销
        return getRepository();
    }

    @Override
    public UpdatingRepositorySupport<E, PK> getUpdatingRepository() {
        //直接转换，避免类型检查造成的开销
        return getRepository();
    }

    @Override
    public CrudRepositorySupport<E, PK> getRepository() {
        //向下转型为CrudRepository，方便使用
        return (CrudRepositorySupport<E, PK>) super.getRepository();
    }
}
