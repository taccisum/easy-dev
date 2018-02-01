package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.web.core.pojo.DTO;

/**
 * @author tac
 * @since 01/02/2018
 */
public interface CrudController<E extends MinEntityStructureAware<PK>, PK, CM extends DTO, UM extends DTO>
        extends
        CreationController<E, PK, CM>,
        DeletionController<E, PK>,
        RetrievalController<E, PK>,
        UpdatingController<E, PK, UM> {
}
