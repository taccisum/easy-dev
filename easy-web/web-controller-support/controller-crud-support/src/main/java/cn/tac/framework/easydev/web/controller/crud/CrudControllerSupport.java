package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.crud.CrudServiceSupport;
import cn.tac.framework.easydev.web.controller.core.ControllerSkeleton;
import cn.tac.framework.easydev.web.core.pojo.DTO;

/**
 * @author tac
 * @since 2.0
 */
public abstract class CrudControllerSupport<E extends MinEntityStructureAware<PK>, PK, CM extends DTO, UM extends DTO>
        extends ControllerSkeleton<E, PK>
        implements
        CreationControllerSupport<E, PK, CM>,
        DeletionControllerSupport<E, PK>,
        RetrievalControllerSupport<E, PK>,
        UpdatingControllerSupport<E, PK, UM> {
    public CrudControllerSupport(CrudServiceSupport<E, PK> service) {
        super(service);
    }
}
