package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;
import cn.tac.framework.easydev.service.crud.UpdatingServiceSupport;
import cn.tac.framework.easydev.web.controller.core.api.ServiceAware;
import cn.tac.framework.easydev.web.controller.crud.api.UpdatingController;
import cn.tac.framework.easydev.web.controller.crud.api.UpdatingServiceAware;
import cn.tac.framework.easydev.web.core.builder.SuccessRestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tac
 * @since 2.0
 */
public interface UpdatingControllerSupport<E extends MinEntityStructureAware<PK>, PK, M>
        extends UpdatingController<E, PK, M>, ServiceAware<E, PK>, UpdatingServiceAware<E, PK> {

    @Override
    @PutMapping("{id}")
    @ApiOperation("根据id修改数据")
    default RestfulApiResponse<E> update(@PathVariable("id") PK id, @RequestBody M model) {
        E entity = convertUpdatingModel2Entity(id, model);
        return new SuccessRestfulApiResponseBuilder<E>()
                .msg("更新数据成功")
                .data(getUpdatingService().updateByPrimaryKeySelective(entity))
                .build();
    }

    @Override
    default UpdatingServiceSupport<E, PK> getUpdatingService() {
        ServiceSkeleton<E, PK> service = getService();
        if (service instanceof UpdatingServiceSupport) {
            return (UpdatingServiceSupport<E, PK>) service;
        } else {
            throw new RuntimeException(service.getClass() + " is not an instance of " + UpdatingServiceSupport.class);
        }
    }

    E convertUpdatingModel2Entity(PK id, M model);
}
