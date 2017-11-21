package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.CrudRepositorySupport;
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
}
