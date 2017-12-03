package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.crud.CreationServiceSupport;

/**
 * @author tac
 * @since 03/12/2017
 */
public interface CreationServiceAware<E extends MinEntityStructureAware<PK>, PK> {
    CreationServiceSupport<E, PK> getCreationService();
}
