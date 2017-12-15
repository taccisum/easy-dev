package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.crud.DeletionServiceSupport;

/**
 * @author tac
 * @since 2.0
 */
public interface DeletionServiceAware<E extends MinEntityStructureAware<PK>, PK> {
    DeletionServiceSupport<E, PK> getDeletionService();
}
