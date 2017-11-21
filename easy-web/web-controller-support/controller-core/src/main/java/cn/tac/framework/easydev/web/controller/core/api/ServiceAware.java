package cn.tac.framework.easydev.web.controller.core.api;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;

/**
 * @author tac
 * @since 2.0
 */
public interface ServiceAware<E extends MinEntityStructureAware<PK>, PK> {
    ServiceSkeleton<E, PK> getService();
}
