package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.dao.crud.CrudRepositorySupport;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;

/**
 * 通用的支持CRUD操作的Service
 *
 * @author tac
 * @since 17/11/2017
 */
public abstract class CrudServiceSupport<E extends MinEntityStructure<PK>, PK> extends ServiceSkeleton<E, PK>
        implements
        CreationServiceSupport<E, PK>,
        DeletionServiceSupport<E, PK>,
        RetrievalServiceSupport<E, PK>,
        UpdatingServiceSupport<E, PK> {
    public CrudServiceSupport(CrudRepositorySupport<E, PK> repository) {
        super(repository);
    }
}
