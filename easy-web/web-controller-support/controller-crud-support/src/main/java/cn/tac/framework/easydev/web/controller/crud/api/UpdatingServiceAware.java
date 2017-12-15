package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.crud.UpdatingServiceSupport;

/**
 * @author tac
 * @since 2.0
 */
public interface UpdatingServiceAware<E extends MinEntityStructureAware<PK>, PK> {
    UpdatingServiceSupport<E, PK> getUpdatingService();
}
