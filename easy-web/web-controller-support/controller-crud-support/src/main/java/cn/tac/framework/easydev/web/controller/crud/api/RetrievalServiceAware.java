package cn.tac.framework.easydev.web.controller.crud.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.crud.RetrievalServiceSupport;

/**
 * @author tac
 * @since 2.0
 */
public interface RetrievalServiceAware<E extends MinEntityStructureAware<PK>, PK> {
    RetrievalServiceSupport<E, PK> getRetrievalService();
}
