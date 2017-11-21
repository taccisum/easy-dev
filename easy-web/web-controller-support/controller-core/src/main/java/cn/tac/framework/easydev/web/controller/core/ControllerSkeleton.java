package cn.tac.framework.easydev.web.controller.core;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;

/**
 * @author tac
 * @since 2.0
 */
public abstract class ControllerSkeleton<E extends MinEntityStructureAware<PK>, PK> implements ServiceAware<E, PK> {
    private ServiceSkeleton service;

    public ControllerSkeleton(ServiceSkeleton<E, PK> service) {
        this.service = service;
    }

    @Override
    public ServiceSkeleton<E, PK> getService() {
        return service;
    }
}
