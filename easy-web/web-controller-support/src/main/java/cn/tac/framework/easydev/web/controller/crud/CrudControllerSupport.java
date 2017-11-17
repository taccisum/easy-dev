package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.service.crud.CrudServiceSupport;
import cn.tac.framework.easydev.web.controller.core.ControllerSkeleton;

/**
 * @author tac
 * @since 17/11/2017
 */
public abstract class CrudControllerSupport<E extends MinEntityStructure<PK>, PK> extends ControllerSkeleton<E, PK>
        implements
        CreationControllerSupport<E, PK>,
        DeletionControllerSupport<E, PK>,
        RetrievalControllerSupport<E, PK>,
        UpdatingControllerSupport<E, PK> {
    public CrudControllerSupport(CrudServiceSupport<E, PK> service) {
        super(service);
    }
}
