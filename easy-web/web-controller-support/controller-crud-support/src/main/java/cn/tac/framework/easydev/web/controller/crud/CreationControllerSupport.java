package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;
import cn.tac.framework.easydev.service.crud.CreationServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.controller.crud.api.CreationServiceAware;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author tac
 * @since 2.0
 */
public interface CreationControllerSupport<E extends MinEntityStructureAware<PK>, PK, M>
        extends ServiceAware<E, PK>, CreationServiceAware<E, PK> {
    @PostMapping
    @ApiOperation("新增数据")
    default RestfulApiResponse<E> insert(@Valid @RequestBody M model) {
        return RestfulApiResponse.success("新增数据成功", getCreationService().insert(convertCreationModel2Entity(model)));
    }

    @Override
    default CreationServiceSupport<E, PK> getCreationService() {
        ServiceSkeleton<E, PK> service = getService();
        if (service instanceof CreationServiceSupport) {
            return (CreationServiceSupport<E, PK>) service;
        } else {
            throw new RuntimeException(service.getClass() + " is not an instance of " + CreationServiceSupport.class);
        }
    }

    E convertCreationModel2Entity(M model);
}
