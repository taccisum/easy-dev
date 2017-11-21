package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.crud.CreationServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface CreationControllerSupport<E extends MinEntityStructureAware<PK>, PK> extends ServiceAware<E, PK> {
    default void checkCreationService() {
        if (!(getService() instanceof CreationServiceSupport)) {
            //todo::
            throw new RuntimeException(getService().getClass() + " is not an instance of " + CreationServiceSupport.class);
        }
    }

    @PostMapping
    @ApiOperation("新增数据")
    RestfulApiResponse<E> insert(@RequestBody E entity);
}
