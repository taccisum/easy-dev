package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;
import cn.tac.framework.easydev.service.crud.CreationServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.controller.crud.api.CreationController;
import cn.tac.framework.easydev.web.controller.crud.api.CreationServiceAware;
import cn.tac.framework.easydev.web.core.builder.SuccessRestfulApiResponseBuilder;
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
        extends CreationController<E, PK, M>, ServiceAware<E, PK>, CreationServiceAware<E, PK> {

    @Override
    @ApiOperation("新增数据")
    default RestfulApiResponse<E> insert(@Valid @RequestBody M model) {
        return new SuccessRestfulApiResponseBuilder<E>()
                .msg("新增数据成功")
                .data(getCreationService().insert(convertCreationModel2Entity(model)))
                .build();
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
